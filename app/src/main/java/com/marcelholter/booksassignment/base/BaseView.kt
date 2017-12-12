package com.marcelholter.booksassignment.base

import com.marcelholter.booksassignment.presentation.base.BaseEvent
import com.marcelholter.booksassignment.presentation.base.BaseViewState
import io.reactivex.Observable

/**
 * Created by mholter on 11/12/2017.
 */
interface BaseView<E : BaseEvent, in VS : BaseViewState> {
  val events: Observable<E>
  fun render(viewState: VS)
}
