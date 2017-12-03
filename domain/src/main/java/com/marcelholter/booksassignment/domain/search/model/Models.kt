package com.marcelholter.booksassignment.domain.search.model

data class VolumePageDomainModel(
    val totalVolumes: Int,
    val volumes: List<VolumeDomainModel>
)

data class VolumeDomainModel(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfoDomainModel
)

data class VolumeInfoDomainModel(
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val descriptions: String,
    val pageCount: Int,
    val printedPageCount: Int,
    val categories: List<String>,
    val maturityRating: String,
    val imageLink: String,
    val language: String,
    val previewLink: String,
    val infoLink: String,
    val canonialVolumeLink: String
)

/**
 * Class hierarchy for all performable actions inside the search feature
 */
sealed class SearchAction {
  // Action to search for volumes based on a query string
  data class SearchVolumesAction(val queryString: String) : SearchAction()
}

/**
 * Class hierarchy for all results inside the search feature
 */
sealed class SearchResult {
  // Class hierarchy for result for searched volumes
  sealed class SearchVolumesResult : SearchResult() {
    // Operation to receive result is being processed
    object InFlight : SearchVolumesResult()
    // Operation to receive result returned an error
    data class Failure(val throwable: Throwable) : SearchVolumesResult()
    // Wrapper for data that was succesfully returned by operation
    data class Success(val volumes: VolumePageDomainModel) : SearchVolumesResult()
  }
}
