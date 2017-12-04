package com.marcelholter.booksassignment.data.search.model

data class VolumePageDataModel(
    val totalVolumes: Int,
    val volumes: List<VolumeDataModel>
)

data class VolumeDataModel(
    val id: String,
    val selfLink: String,
    val volumeInfo: VolumeInfoDataModel
)

data class VolumeInfoDataModel(
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
