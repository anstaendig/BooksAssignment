package com.marcelholter.booksassignment.domain.search.model

data class VolumePageDomainModel(
    val totalVolumes: Int,
    val volumes: List<VolumeDomainModel>
)

data class VolumeDomainModel(
    val id: String?,
    val title: String,
    val subtitle: String,
    val authors: List<String>,
    val publisher: String,
    val publishedDate: String,
    val description: String,
    val pageCount: Int,
    val mainCategory: String,
    val categories: List<String>,
    val averageRating: Float,
    val ratingsCount: Int,
    val imageLinks: ImageLinksDomainModel,
    val language: String,
    val previewLink: String?,
    val canonicalVolumeLink: String,
    val searchTextSnippet: String
)

data class ImageLinksDomainModel(
    val thumbnail: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val smallThumbnail: String?,
    val extraLarge: String?
)
