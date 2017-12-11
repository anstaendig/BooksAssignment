package com.marcelholter.booksassignment.presentation.search.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class VolumePagePresentationModel(
    val totalVolumes: Int,
    val volumes: List<VolumePresentationModel>
)

@Parcelize
data class VolumePresentationModel(
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
    val imageLinks: ImageLinksPresentationModel,
    val language: String,
    val previewLink: String?,
    val canonicalVolumeLink: String,
    val searchTextSnippet: String
) : Parcelable

@Parcelize
data class ImageLinksPresentationModel(
    val thumbnail: String?,
    val small: String?,
    val medium: String?,
    val large: String?,
    val smallThumbnail: String?,
    val extraLarge: String?
) : Parcelable
