package com.marcelholter.booksassignment.util

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader.TileMode
import android.net.Uri
import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation


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

/**
 *
 */
fun ImageView.loadUriCircle(uri: Uri) {
  Picasso.with(context)
      .load(uri)
      .placeholder(android.R.color.darker_gray)
      .transform(CircleTransform())
      .into(this)
}

fun ImageView.loadUriCircle(uri: String) = loadUriCircle(Uri.parse(uri))

private class CircleTransform : Transformation {

  override fun transform(source: Bitmap): Bitmap {
    val size = Math.min(source.width, source.height)

    val x = (source.width - size) / 2
    val y = (source.height - size) / 2

    val squaredBitmap = Bitmap.createBitmap(source, x, y, size, size)
    if (squaredBitmap != source) {
      source.recycle()
    }

    val bitmap = Bitmap.createBitmap(size, size, source.config)

    val canvas = Canvas(bitmap)
    val paint = Paint()
    val shader = BitmapShader(squaredBitmap, TileMode.CLAMP, TileMode.CLAMP)
    paint.shader = shader
    paint.isAntiAlias = true

    val r = size / 2f
    canvas.drawCircle(r, r, r, paint)

    squaredBitmap.recycle()
    return bitmap
  }

  override fun key() = "circle"
}
