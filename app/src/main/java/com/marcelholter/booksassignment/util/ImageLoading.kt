package com.marcelholter.booksassignment.util

import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso


/* Extension functions to load images via Picasso */

/**
 * Load image into [ImageView] based on [Uri]
 *
 * Placeholder for image is a gray color.
 */
fun ImageView.loadUri(uri: Uri) {
  Picasso.with(context)
      .load(uri)
      .placeholder(android.R.color.darker_gray)
      .into(this)
}

/**
 * Helper method to be able to pass in a uri as [String]
 */
fun ImageView.loadUri(uri: String) = loadUri(Uri.parse(uri))
