package com.marcelholter.booksassignment.util

/*
 * Copyright 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * https://github.com/ianhanniballake/cheesesquare/commit/aefa8b57e61266e4ad51bef36e669d69f7fd749c
 * https://guides.codepath.com/android/floating-action-buttons
 *
 */

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.FloatingActionButton
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.View

/**
 * Hide the [FloatingActionButton] when content is being scrolled.
 */
class ScrollAwareFloatingActionButtonBehavior(
    context: Context,
    attrs: AttributeSet
) : FloatingActionButton.Behavior() {

  override fun onStartNestedScroll(
      coordinatorLayout: CoordinatorLayout,
      child: FloatingActionButton,
      directTargetChild: View,
      target: View,
      scrollAxes: Int,
      nestedScrollType: Int
  ): Boolean {
    // Ensure we react to vertical scrolling
    return scrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL
        || super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target,
        scrollAxes, nestedScrollType)
  }

  override fun onNestedScroll(
      coordinatorLayout: CoordinatorLayout,
      child: FloatingActionButton,
      target: View,
      dxConsumed: Int,
      dyConsumed: Int,
      dxUnconsumed: Int,
      dyUnconsumed: Int,
      nestedScrollType: Int
  ) {
    super.onNestedScroll(
        coordinatorLayout,
        child,
        target,
        dxConsumed,
        dyConsumed,
        dxUnconsumed,
        dyUnconsumed,
        nestedScrollType
    )

    if (dyConsumed > 0 && child.visibility == View.VISIBLE) {
      child.hide(object : FloatingActionButton.OnVisibilityChangedListener() {
        override fun onHidden(fab: FloatingActionButton) {
          super.onHidden(fab)
          fab.visibility = View.INVISIBLE
        }
      })
    } else if (dyConsumed < 0 && child.visibility != View.VISIBLE) {
      child.show()
    }
  }
}
