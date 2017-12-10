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
import com.marcelholter.booksassignment.presentation.search.SearchEvent.OnLoadMore
import com.marcelholter.booksassignment.presentation.search.SearchEvent.OnSearchCleared
import com.marcelholter.booksassignment.presentation.search.SearchEvent.OnSearchClicked
import com.marcelholter.booksassignment.presentation.search.mapper.VolumeMapper
import com.marcelholter.booksassignment.presentation.search.model.VolumePagePresentationModel
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class SearchViewModel
@Inject constructor(
    private val searchVolumesUseCase: SearchVolumesUseCase,
    private val volumeMapper: VolumeMapper
) : ViewModel(), BaseViewModel<SearchEvent, SearchViewState> {
  override val eventStream: PublishRelay<SearchEvent> = PublishRelay.create()
  override val viewState: Observable<SearchViewState>
    get() {
      return eventStream
          // Event -> Action
          .compose(actions)
          // Action -> Result
          .compose(results)
          // Cache view state and reduce it to create a new state emission and the latest Result
          // emitted from use cases.
          .scan<SearchViewState>(
              SearchViewState(false, false, null, null),
              reducer())
          // Emit last emission again so that view gets latest state after configuration change.
          .replay(1)
          // Stream shall stay alive even if view unsubscribes/disconnects.
          .autoConnect(0)
    }

  // Transform stream from search click event to corresponding action
  private val searchVolumesAction: ObservableTransformer<SearchEvent.OnSearchClicked, SearchAction.SearchVolumesAction> =
      ObservableTransformer { event ->
        event.map { SearchVolumesAction(it.queryString) }
      }

  // Transform stream from OnLoadMore event to corresponding actin
  private val loadNextPageActionAction: ObservableTransformer<SearchEvent.OnLoadMore, SearchAction.LoadNextPageAction> =
      ObservableTransformer { event ->
        event.map { LoadNextPageAction(it.startIndex) }
      }

  // Transform OnSearchCleared event stream into ClearSearchAction stream
  private val clearSearchAction: ObservableTransformer<SearchEvent.OnSearchCleared, SearchAction.ClearSearchAction> =
      ObservableTransformer { event ->
        event.map { ClearSearchAction }
      }

  // Map each individual SearchEvent to it's SearchAction and combine in one stream
  private val actions: ObservableTransformer<SearchEvent, SearchAction> =
      ObservableTransformer { events: Observable<SearchEvent> ->
        events.publish { event: Observable<SearchEvent> ->
          Observable.merge(
              // Distinct to forbid search same query after itself
              event.ofType(OnSearchClicked::class.java)
                  .distinctUntilChanged()
                  .compose(searchVolumesAction),
              event.ofType(OnLoadMore::class.java)
                  .compose(loadNextPageActionAction),
              event.ofType(OnSearchCleared::class.java)
                  .compose(clearSearchAction)
          )
        }
      }

  // Map each individual SearchAction to it's business logic that returns SearchResult and combine
  // in one stream
  private val results: ObservableTransformer<SearchAction, SearchResult> =
      ObservableTransformer { actions: Observable<SearchAction> ->
        actions.publish { action: Observable<SearchAction> ->
          Observable.merge(
              action.ofType(SearchAction.SearchVolumesAction::class.java)
                  .compose(searchVolumesUseCase.getSearchVolumesResult()),
              action.ofType(SearchAction.LoadNextPageAction::class.java)
                  .compose(searchVolumesUseCase.getLoadNextPageResult()),
              action.ofType(SearchAction.ClearSearchAction::class.java)
                  .map { SearchResult.ClearSearchResult }
          )
        }
      }

  /**
   * Reduce previous view state and current result into new view state
   */
  private fun reducer(): BiFunction<SearchViewState, SearchResult, SearchViewState> =
      BiFunction { previousState, result ->
        when (result) {
          is SearchResult.SearchVolumesResult.InFlight ->
            previousState.copy(loading = true)
          is SearchResult.SearchVolumesResult.Failure ->
            previousState.copy(loading = false, error = result.throwable)
          is SearchResult.SearchVolumesResult.Success ->
            SearchViewState(
                loading = false,
                loadingNextPage = false,
                error = null,
                volumePage = VolumePagePresentationModel(
                    result.volumes.totalVolumes,
                    result.volumes.volumes.map { volumeMapper.mapToPresentationModel(it) }
                )
            )
          is SearchResult.NextPageResult.InFlight ->
            previousState.copy(
                loadingNextPage = true
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
                volumePage = VolumePagePresentationModel(
                    result.volumes.totalVolumes,
                    previousState.volumePage!!.volumes + result.volumes.volumes.map {
                      volumeMapper.mapToPresentationModel(it)
                    }
                )
            )
          is SearchResult.ClearSearchResult ->
            SearchViewState(
                loading = false,
                loadingNextPage = false,
                error = null,
                volumePage = null
            )
        }
      }
}


