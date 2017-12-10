package com.marcelholter.booksassignment.domain.search

import com.marcelholter.booksassignment.domain.search.SearchAction.LoadNextPageAction
import com.marcelholter.booksassignment.domain.search.SearchAction.SearchVolumesAction
import com.marcelholter.booksassignment.domain.search.SearchResult.NextPageResult
import com.marcelholter.booksassignment.domain.search.SearchResult.SearchVolumesResult
import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Scheduler
import javax.inject.Inject
import javax.inject.Named

/**
 * Interface definition of the use case to search for volumes
 */
interface SearchVolumesUseCase {
  /**
   * Map operation [SearchRepository.searchVolumes] to [SearchVolumesAction] and emit data as
   * [SearchVolumesResult].
   */
  fun getSearchVolumesResult():
      ObservableTransformer<SearchAction.SearchVolumesAction, SearchResult.SearchVolumesResult>
  fun getLoadNextPageResult():
      ObservableTransformer<SearchAction.LoadNextPageAction, SearchResult.NextPageResult>
}

class SearchVolumesUseCaseImpl
@Inject constructor(
    private val searchRepository: SearchRepository,
    @Named("io") private val backgroundScheduler: Scheduler,
    @Named("mainThread") private val mainThread: Scheduler
) : SearchVolumesUseCase {
  private var queryString = ""

  override fun getSearchVolumesResult():
      ObservableTransformer<SearchVolumesAction, SearchVolumesResult> {
    return ObservableTransformer { actions: Observable<SearchAction.SearchVolumesAction> ->
      actions.flatMap { action: SearchVolumesAction ->
        queryString = action.queryString
        searchRepository.searchVolumes(action.queryString)
            // Move execution on injected background scheduler
            .subscribeOn(backgroundScheduler)
            // Transform to observable to emit beginning InFlight result
            .toObservable()
            // Map the data to the success result case
            .map<SearchResult.SearchVolumesResult> { volumes: VolumePageDomainModel ->
              SearchResult.SearchVolumesResult.Success(volumes)
            }
            // Catch error and map it to result failure case
            .onErrorReturn { throwable: Throwable ->
              SearchResult.SearchVolumesResult.Failure(throwable)
            }
            // Post back to injected main thread
            .observeOn(mainThread)
            // Start emission with result InFlight object to indicate that action is being processed
            .startWith(SearchResult.SearchVolumesResult.InFlight)
      }
    }
  }

  override fun getLoadNextPageResult(): ObservableTransformer<LoadNextPageAction, NextPageResult> {
    return ObservableTransformer { actions: Observable<SearchAction.LoadNextPageAction> ->
      actions.flatMap { action: LoadNextPageAction ->
        searchRepository.searchVolumes(queryString, action.startIndex)
            // Move execution on injected background scheduler
            .subscribeOn(backgroundScheduler)
            // Transform to observable to emit beginning InFlight result
            .toObservable()
            // Map the data to the success result case
            .map<SearchResult.NextPageResult> { volumes: VolumePageDomainModel ->
              SearchResult.NextPageResult.Success(volumes)
            }
            // Catch error and map it to result failure case
            .onErrorReturn { throwable: Throwable ->
              SearchResult.NextPageResult.Failure(throwable)
            }
            // Post back to injected main thread
            .observeOn(mainThread)
            // Start emission with result InFlight object to indicate that action is being processed
            .startWith(SearchResult.NextPageResult.InFlight)
      }
    }
  }
}
