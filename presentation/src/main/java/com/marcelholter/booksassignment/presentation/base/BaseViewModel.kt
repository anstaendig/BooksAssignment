package com.marcelholter.booksassignment.presentation.base

import io.reactivex.Observable

/**
 * ViewModel interface that will subscribe to the views events, process them and emit a new state
 * based on that stream.
 */
interface BaseViewModel<E : BaseEvent, VS : BaseViewState> {
  // Bind event stream to view model
  fun processEvents(events: Observable<E>)

  // View state stream for ui to render
  val viewStates: Observable<VS>
}
