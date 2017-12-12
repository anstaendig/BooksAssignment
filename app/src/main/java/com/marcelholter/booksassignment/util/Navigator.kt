package com.marcelholter.booksassignment.util

import android.content.Intent
import android.net.Uri
import android.support.v4.app.ActivityOptionsCompat
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.marcelholter.booksassignment.detail.VolumeDetailsActivity
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel

/* Navigation helper extension functions */

fun AppCompatActivity.navigateToVolumeDetail(
    volume: VolumePresentationModel,
    coverImageView: View
) {
  val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
      this,
      coverImageView,
      "vh_volume_image_view_cover"
  )
  startActivity(VolumeDetailsActivity.newIntent(this, volume), options.toBundle())
}

fun AppCompatActivity.navigateToUrl(url: Uri) {
  Intent(Intent.ACTION_VIEW, url).run { startActivity(this) }
}

fun AppCompatActivity.navigateToUrl(url: String) {
  navigateToUrl(Uri.parse(url))
}

