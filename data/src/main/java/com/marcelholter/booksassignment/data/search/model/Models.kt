package com.marcelholter.booksassignment.data.search.model

data class VolumeJson(
    val id: String,
    val volumeInfo: VolumeInfoJson,
    val searchInfo: SearchInfoJson?
)

data class VolumeInfoJson(
    val title: String,
    val subtitle: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int?,
    val mainCategory: String?,
    val categories: List<String>?,
    val averageRating: Float?,
    val ratingsCount: Int?,
    val imageLinks: ImageLinksDataModel?,
    val language: String?,
    val previewLink: String?,
    val canonicalVolumeLink: String
)

data class SearchInfoJson(
    val textSnippet: String?
)

data class VolumePageDataModel(
    val items: List<VolumeDataModel>,
    val totalItems: Int
)

data class VolumeDataModel(
    val id: String,
    val title: String,
    val subtitle: String?,
    val authors: List<String>?,
    val publisher: String?,
    val publishedDate: String?,
    val description: String?,
    val pageCount: Int?,
    val mainCategory: String?,
    val categories: List<String>?,
    val averageRating: Float?,
    val ratingsCount: Int?,
    val imageLinks: ImageLinksDataModel?,
    val language: String?,
    val previewLink: String?,
    val canonicalVolumeLink: String,
    val searchTextSnippet: String?
)

data class ImageLinksDataModel(
    val thumbnail: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val smallThumbnail: String?,
    val extraLarge: String?
)

