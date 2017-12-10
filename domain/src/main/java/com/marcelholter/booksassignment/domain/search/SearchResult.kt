package com.marcelholter.booksassignment.domain.search

import com.marcelholter.booksassignment.domain.base.BaseResult
import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel

/**
 * Class hierarchy for all results inside the search feature
 */
sealed class SearchResult : BaseResult {
  // Class hierarchy for result for searched volumes
  sealed class SearchVolumesResult : SearchResult() {
    // Operation to receive result is being processed
    object InFlight : SearchVolumesResult()

    // Operation to receive result returned an error
    data class Failure(val throwable: Throwable) : SearchVolumesResult()

    // Wrapper for data that was successfully returned by operation
    data class Success(val volumes: VolumePageDomainModel) : SearchVolumesResult()
  }

  sealed class NextPageResult : SearchResult() {
    // Operation to receive result is being processed
    object InFlight : NextPageResult()

    // Operation to receive result returned an error
    data class Failure(val throwable: Throwable) : NextPageResult()

    // Wrapper for data that was successfully returned by operation
    data class Success(val volumes: VolumePageDomainModel) : NextPageResult()
  }

  object ClearSearchResult : SearchResult()
}
