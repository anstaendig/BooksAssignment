package com.marcelholter.booksassignment.presentation.search

import com.marcelholter.booksassignment.presentation.base.BaseEvent

sealed class SearchEvent : BaseEvent {
  data class OnSearchClicked(val queryString: String) : SearchEvent()
  data class OnLoadMore(val startIndex: Int): SearchEvent()
  object OnSearchCleared : SearchEvent()
}
