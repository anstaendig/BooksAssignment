package com.marcelholter.booksassignment.data.search

import com.marcelholter.booksassignment.data.search.model.VolumeDataModel
import com.marcelholter.booksassignment.data.search.model.VolumeInfoDataModel
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
    return VolumePageDataModel(100, volumes)
  }

  fun makeVolumeDataModel(): VolumeDataModel {
    return VolumeDataModel(
        "id",
        "selfLink",
        makeVolumeInfoDataModel()
    )
  }

  fun makeVolumeInfoDataModel(): VolumeInfoDataModel {
    return VolumeInfoDataModel(
        "title",
        "subtitle",
        listOf("author1", "author2"),
        "publisher",
        "26.12.2015",
        "description",
        140,
        150,
        listOf("category1", "category2"),
        "maturityRating",
        "imageLink",
        "language",
        "previewLink",
        "infoLink",
        "canonialVolumeLink"
    )
  }
}
