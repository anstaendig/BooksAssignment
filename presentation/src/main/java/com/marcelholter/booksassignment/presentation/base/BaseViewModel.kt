package com.marcelholter.booksassignment.presentation.base

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * ViewModel interface that will subscribe to the views events, process them and emit a new state
 * based on that stream.
 */
interface BaseViewModel<E : BaseEvent, VS : BaseViewState> {
  val eventStream: PublishRelay<E>
  val viewState: Observable<VS>
}
