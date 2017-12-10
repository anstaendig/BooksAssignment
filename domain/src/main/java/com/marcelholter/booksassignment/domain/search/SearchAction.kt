package com.marcelholter.booksassignment.domain.search

import com.marcelholter.booksassignment.domain.base.BaseAction

/**
 * Class hierarchy for all performable actions inside the search feature
 */
sealed class SearchAction : BaseAction {
  // Action to search for volumes based on a query string
  data class SearchVolumesAction(val queryString: String) : SearchAction()
  data class LoadNextPageAction(val startIndex: Int): SearchAction()
  object ClearSearchAction: SearchAction()
}
