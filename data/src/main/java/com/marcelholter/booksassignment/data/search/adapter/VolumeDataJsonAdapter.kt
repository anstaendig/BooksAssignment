package com.marcelholter.booksassignment.data.search.adapter

import com.marcelholter.booksassignment.data.search.model.VolumeDataModel
import com.marcelholter.booksassignment.data.search.model.VolumeJson
import com.squareup.moshi.FromJson

/**
 * Moshi adapter to unwrap inner json object for ease of use later on.
 */
class VolumeDataJsonAdapter {
  @FromJson
  fun fromJson(volumeJson: VolumeJson): VolumeDataModel {
    return VolumeDataModel(
        volumeJson.id,
        volumeJson.volumeInfo.title,
        volumeJson.volumeInfo.subtitle,
        volumeJson.volumeInfo.authors,
        volumeJson.volumeInfo.publisher,
        volumeJson.volumeInfo.publishedDate,
        volumeJson.volumeInfo.description,
        volumeJson.volumeInfo.pageCount,
        volumeJson.volumeInfo.mainCategory,
        volumeJson.volumeInfo.categories,
        volumeJson.volumeInfo.averageRating,
        volumeJson.volumeInfo.ratingsCount,
        volumeJson.volumeInfo.imageLinks,
        volumeJson.volumeInfo.language,
        volumeJson.volumeInfo.previewLink,
        volumeJson.volumeInfo.canonicalVolumeLink,
        volumeJson.searchInfo?.textSnippet
    )
  }
}
