package com.marcelholter.booksassignment.detail

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import com.marcelholter.booksassignment.R
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel
import com.marcelholter.booksassignment.util.bindView
import com.marcelholter.booksassignment.util.loadUri
import dagger.android.support.DaggerAppCompatActivity

private const val EXTRA_VOLUME = "EXTRA_VOLUME"

class DetailActivity : DaggerAppCompatActivity() {
  companion object {
    fun newIntent(): Intent {
      return Intent()
    }
  }

  private val coverImageView: ImageView by bindView(R.id.a_detail_image_view_cover)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.a_detail)
    val volume: VolumePresentationModel = intent.extras.getParcelable(EXTRA_VOLUME)
    volume.imageLinks.thumbnail?.let { coverImageView.loadUri(it) }
  }
}
