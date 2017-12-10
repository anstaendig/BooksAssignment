package com.marcelholter.booksassignment.data.search

import com.marcelholter.booksassignment.data.search.model.ImageLinksDataModel
import com.marcelholter.booksassignment.data.search.model.VolumeDataModel
import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel

/**
 * Factory class to generate models for unit tests
 */
object VolumeFactory {
  fun makeVolumePageDataModel(size: Int): VolumePageDataModel {
    val volumes: MutableList<VolumeDataModel> = mutableListOf()
    repeat(size) {
      volumes.add(makeVolumeDataModel())
    }
    return VolumePageDataModel(volumes, 100)
  }

  fun makeVolumeDataModel(): VolumeDataModel {
    return VolumeDataModel(
        "id",
        "title",
        "subtitle",
        listOf("author1", "author2"),
        "publisher",
        "26.12.2015",
        "description",
        140,
        "mainCategory",
        listOf("category1", "category2"),
        2.5F,
        150,
        ImageLinksDataModel(
            "thumbnail",
            "small",
            "medium",
            "large",
            "smallThumbnail",
            "extraLarge"
        ),
        "language",
        "previewLink",
        "canonicalVolumeLink",
        "searchTextSnippet"
    )
  }
}
