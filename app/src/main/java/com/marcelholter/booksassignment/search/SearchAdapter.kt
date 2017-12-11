package com.marcelholter.booksassignment.search

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.marcelholter.booksassignment.R
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel
import com.marcelholter.booksassignment.search.SearchAdapter.VolumeViewHolder
import com.marcelholter.booksassignment.util.bindView
import com.marcelholter.booksassignment.util.loadUri

// Named type for function that takes volume as parameter for click actions
typealias OnVolumeClick = (VolumePresentationModel) -> Unit

/**
 * Adapter for volumes
 *
 * @param[onVolumeClick] Function for click actions on volumes
 */
class SearchAdapter(
    private val onVolumeClick: OnVolumeClick
) : RecyclerView.Adapter<VolumeViewHolder>() {
  private var volumes: List<VolumePresentationModel> = emptyList()

  override fun getItemCount(): Int = volumes.size

  override fun onBindViewHolder(holder: VolumeViewHolder, position: Int) {
    val volume = volumes[position]
    holder.itemView.setOnClickListener { onVolumeClick(volume) }
    volume.imageLinks.thumbnail?.let { holder.coverImageView.loadUri(it) }
    holder.titleTextView.text = volume.title
    holder.authorTextView.text = volume.authors.joinToString(", ")
    holder.ratingBar.rating = volume.averageRating
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VolumeViewHolder {
    return VolumeViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.vh_volume, parent, false)
    )
  }

  /**
   * Add volumes to adapter
   *
   * Uses [DiffUtil] to compare old and new lists and dispatches calculated difference to adapter
   */
  fun setVolumes(volumes: List<VolumePresentationModel>) {
    val diffResult = DiffUtil.calculateDiff(VolumesDiffUtilCallback(this.volumes, volumes))
    diffResult.dispatchUpdatesTo(this)
    this.volumes = volumes
  }

  /**
   * ViewHolder for volumes
   */
  class VolumeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val coverImageView: ImageView by bindView(R.id.vh_volume_image_view_cover)
    val titleTextView: TextView by bindView(R.id.vh_volume_text_view_title)
    val authorTextView: TextView by bindView(R.id.vh_volume_text_view_author)
    val ratingBar: RatingBar by bindView(R.id.vh_volume_rating_bar)
  }

  /**
   * Callback for [DiffUtil.calculateDiff] to calculate difference of old and new list.
   */
  private class VolumesDiffUtilCallback(
      private val oldList: List<VolumePresentationModel>,
      private val newList: List<VolumePresentationModel>
  ) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldList[oldItemPosition] == newList[newItemPosition]
    }
  }
}


