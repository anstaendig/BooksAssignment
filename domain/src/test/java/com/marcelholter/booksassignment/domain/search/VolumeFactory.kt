package com.marcelholter.booksassignment.domain.search

import com.marcelholter.booksassignment.domain.search.model.VolumeDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumeInfoDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel

/**
 * Factory class to generate models for unit tests
 */
object VolumeFactory {
  fun makeVolumePageDomainModel(size: Int): VolumePageDomainModel {
    val volumes: MutableList<VolumeDomainModel> = mutableListOf()
    repeat(size) {
      volumes.add(makeVolumeDomainModel())
    }
    return VolumePageDomainModel(100, volumes)
  }

  fun makeVolumeDomainModel(): VolumeDomainModel {
    return VolumeDomainModel(
        "id",
        "selfLink",
        makeVolumeInfoDomainModel()
    )
  }

  fun makeVolumeInfoDomainModel(): VolumeInfoDomainModel {
    return VolumeInfoDomainModel(
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
