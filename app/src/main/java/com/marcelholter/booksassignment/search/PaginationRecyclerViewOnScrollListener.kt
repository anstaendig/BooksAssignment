package com.marcelholter.booksassignment.search

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * [RecyclerView.OnScrollListener] to support pagination.
 *
 * @param[layoutManager] [LinearLayoutManager] to query items
 * @param[onLoadMore] Function with startIndex as [Int] parameter as callback to load more data
 */
class PaginationRecyclerViewOnScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val onLoadMore: (Int) -> Unit
) : RecyclerView.OnScrollListener() {
  // Item threshold before loading next page
  private val visibleThreshold = 5
  // StartIndex to load specific page
  private var startIndex = 0
  // The total number of items in the data set after the last load
  private var previousTotalItemCount = 0
  // Loading indication
  private var loading = true
  // Total amount of items in remote data set
  var totalItemCount = 0

  // This happens many times a second during a scroll, so be wary of the code you place here.
  // We are given a few useful parameters to help us work out if we need to load some more data,
  // but first we check if we are waiting for the previous load to finish.
  override fun onScrolled(view: RecyclerView?, dx: Int, dy: Int) {
    // Ignore scrolling upwards
    if (dy < 1) return

    val currentItemCount = layoutManager.itemCount

    // Check if current amount of items in data set is equal to amout of items in data source
    if (currentItemCount >= totalItemCount) return

    val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

    // If it’s still loading, we check to see if the data set count has
    // changed, if so we conclude it has finished loading and update the current page
    // number and total item count.
    if (loading && currentItemCount > previousTotalItemCount) {
      loading = false
      previousTotalItemCount = currentItemCount
    }
    // If it isn’t currently loading, we check to see if we have breached
    // the visibleThreshold and need to reload more data.
    // If we do need to reload some more data, we execute onLoadMore to fetch the data.
    if (!loading && lastVisibleItemPosition + visibleThreshold > currentItemCount) {
      // Set startIndex for next page
      startIndex = currentItemCount
      onLoadMore(startIndex)
      loading = true
    }
  }

  // Call this method whenever performing new searches
  fun resetState() {
    startIndex = 0
    previousTotalItemCount = 0
    loading = true
  }
}
