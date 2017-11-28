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

sealed class SearchAction {
  data class SearchVolumesAction(val queryString: String) : SearchAction()
}

sealed class SearchResult {
  sealed class SearchVolumesResult : SearchResult() {
    object InFlight : SearchVolumesResult()
    data class Failure(val throwable: Throwable) : SearchVolumesResult()
    data class Success(val volumes: VolumePageDomainModel) : SearchVolumesResult()
  }
}
