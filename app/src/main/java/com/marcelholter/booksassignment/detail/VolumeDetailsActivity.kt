package com.marcelholter.booksassignment.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.marcelholter.booksassignment.R
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel
import com.marcelholter.booksassignment.util.bindView
import com.marcelholter.booksassignment.util.loadUri
import com.marcelholter.booksassignment.util.navigateToUrl

/**
 * Activity that shows details of a given volume
 */
class VolumeDetailsActivity : AppCompatActivity() {
  companion object {
    private const val EXTRA_VOLUME = "EXTRA_VOLUME"

    /**
     * Creates an [Intent] to start this activity with given volume
     */
    fun newIntent(context: Context, volume: VolumePresentationModel): Intent {
      return Intent(context, VolumeDetailsActivity::class.java).apply {
        putExtra(EXTRA_VOLUME, volume)
      }
    }
  }

  private val coverImageView: ImageView by bindView(R.id.a_detail_image_view_cover)
  private val titleTextView: TextView by bindView(R.id.a_detail_text_view_title)
  private val publishedDateTextView: TextView by bindView(
      R.id.a_detail_text_view_label_published_date)
  private val authorsTextView: TextView by bindView(R.id.a_detail_text_view_authors)
  private val ratinBar: RatingBar by bindView(R.id.a_detail_rating_bar)
  private val ratingsTextView: TextView by bindView(R.id.a_detail_text_view_rating)
  private val publisherTextView: TextView by bindView(R.id.a_detail_text_view_publisher)
  private val previewFloatingActionButton: FloatingActionButton by bindView(
      R.id.a_detail_floating_action_button_preview)
  private val descriptionTextView: TextView by bindView(R.id.a_detail_text_view_description)
  private val googlePlayImageView: ImageView by bindView(R.id.a_detai_image_view_google_play)

  private var volume: VolumePresentationModel? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.a_detail)
    if (savedInstanceState == null) {
      if (!intent.hasExtra(EXTRA_VOLUME)) {
        throw IllegalArgumentException("No volume provided")
      }
      volume = intent.extras.getParcelable(EXTRA_VOLUME)
    } else {
      volume = savedInstanceState.getParcelable(EXTRA_VOLUME)
      if (volume == null) throw IllegalArgumentException("No volume provided")
    }
    volume?.let { renderVolume(it) }
  }

  override fun onSaveInstanceState(outState: Bundle) {
    outState.putParcelable(EXTRA_VOLUME, volume)
    super.onSaveInstanceState(outState)
  }

  private fun renderVolume(volume: VolumePresentationModel) {
    volume.imageLinks.thumbnail?.let { coverImageView.loadUri(it) }
    titleTextView.text = volume.title
    publishedDateTextView.text = volume.publishedDate
    authorsTextView.text = volume.authors.joinToString("\n")
    ratinBar.rating = volume.averageRating
    ratingsTextView.text = "Ratings: ${volume.ratingsCount}"
    publisherTextView.text = volume.publisher
    volume.previewLink?.let { url ->
      with(previewFloatingActionButton) {
        show()
        setOnClickListener {
          this@VolumeDetailsActivity.navigateToUrl(url)
        }
      }
    }
    descriptionTextView.text = volume.description
    googlePlayImageView.setOnClickListener {
      navigateToUrl(volume.canonicalVolumeLink)
    }
  }
}
