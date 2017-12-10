package com.marcelholter.booksassignment.util

import android.view.View

/* Useful extension functions on Views */

fun View.show() {
  this.visibility = View.VISIBLE
}

fun View.go() {
  this.visibility = View.GONE
}

