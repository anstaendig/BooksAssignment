package com.marcelholter.booksassignment.presentation.base

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * ViewModel interface that will subscribe to the views events, process them and emit a new state
 * based on that stream.
 */
interface BaseViewModel<E : BaseEvent, VS : BaseViewState> {
  // Relay for event stream from view
  val eventsRelay: PublishRelay<E>
  // View state stream for ui to render
  val viewStates: Observable<VS>
}
