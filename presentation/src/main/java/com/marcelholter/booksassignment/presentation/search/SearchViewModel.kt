package com.marcelholter.booksassignment.presentation.search

import android.arch.lifecycle.ViewModel
import com.jakewharton.rxrelay2.PublishRelay
import com.marcelholter.booksassignment.domain.search.SearchAction
import com.marcelholter.booksassignment.domain.search.SearchAction.ClearSearchAction
import com.marcelholter.booksassignment.domain.search.SearchAction.LoadNextPageAction
import com.marcelholter.booksassignment.domain.search.SearchAction.SearchVolumesAction
import com.marcelholter.booksassignment.domain.search.SearchResult
import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCase
import com.marcelholter.booksassignment.presentation.base.BaseViewModel
import com.marcelholter.booksassignment.presentation.search.mapper.VolumeMapper
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class SearchViewModel
@Inject constructor(
    private val searchVolumesUseCase: SearchVolumesUseCase,
    private val volumeMapper: VolumeMapper
) : ViewModel(), BaseViewModel<SearchEvent, SearchViewState> {
  private val initialState = SearchViewState(
      loading = false,
      loadingNextPage = false,
      error = null,
      totalVolumes = 0,
      volumes = emptyList()
  )

  override val eventsRelay: PublishRelay<SearchEvent> = PublishRelay.create()
  override val viewStates: Observable<SearchViewState> =
      eventsRelay
          // Event -> Action
          .map { event -> actionFromEvent(event) }
          // Action -> Result
          .compose(results())
          // Cache view state and reduce it to create a new state emission and the latest Result
          // emitted from use cases.
          .scan<SearchViewState>(
              initialState,
              reducer())
          // Emit last emission again so that view gets latest state after configuration change.
          .replay(1)
          // Stream shall stay alive even if view unsubscribes/disconnects.
          .autoConnect(0)

  /**
   * Map event to it's specific action
   */
  private fun actionFromEvent(event: SearchEvent) = when (event) {
    is SearchEvent.OnSearchClicked -> SearchVolumesAction(event.queryString)
    is SearchEvent.OnLoadMore -> LoadNextPageAction(event.startIndex)
    is SearchEvent.OnSearchCleared -> ClearSearchAction
  }

  /**
   * Transform action stream into result stream
   */
  private fun results(): ObservableTransformer<SearchAction, SearchResult> {
    return ObservableTransformer { actions: Observable<SearchAction> ->
      actions.publish { shared: Observable<SearchAction> ->
        Observable.merge(
            shared.ofType(SearchAction.SearchVolumesAction::class.java)
                .compose(searchVolumesUseCase.searchVolumesResult),
            shared.ofType(SearchAction.LoadNextPageAction::class.java)
                .compose(searchVolumesUseCase.loadNextPageResult)
        )
      }
    }
  }

  /**
   * Reduce previous view state and current result into new view state
   */
  private fun reducer(): BiFunction<SearchViewState, SearchResult, SearchViewState> =
      BiFunction { previousState, result ->
        when (result) {
          is SearchResult.SearchVolumesResult.InFlight ->
            previousState.copy(
                loading = true,
                error = null
            )
          is SearchResult.SearchVolumesResult.Failure ->
            previousState.copy(
                loading = false,
                error = result.throwable
            )
          is SearchResult.SearchVolumesResult.Success ->
            SearchViewState(
                loading = false,
                loadingNextPage = false,
                error = null,
                totalVolumes = result.volumes.totalVolumes,
                volumes = result.volumes.volumes.map { volumeMapper.mapToPresentationModel(it) }
            )
          is SearchResult.NextPageResult.InFlight ->
            previousState.copy(
                loadingNextPage = true,
                error = null
            )
          is SearchResult.NextPageResult.Failure ->
            previousState.copy(
                loadingNextPage = false,
                error = result.throwable
            )
          is SearchResult.NextPageResult.Success ->
            previousState.copy(
                loadingNextPage = false,
                error = null,
                volumes = previousState.volumes + result.volumes.volumes.map {
                  volumeMapper.mapToPresentationModel(it)
                }
            )
          is SearchResult.ClearSearchResult -> initialState
        }
      }
}


