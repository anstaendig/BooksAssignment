package com.marcelholter.booksassignment.util

import android.view.View

/* Useful extension functions on Views */

fun View.show() {
  visibility = View.VISIBLE
}

fun View.hide() {
  visibility = View.INVISIBLE
}

fun View.go() {
  visibility = View.GONE
}

