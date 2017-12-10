package com.marcelholter.booksassignment.presentation.search

import com.marcelholter.booksassignment.domain.search.model.VolumeDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumeInfoDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import com.marcelholter.booksassignment.presentation.search.model.VolumeInfoPresentationModel
import com.marcelholter.booksassignment.presentation.search.model.VolumePagePresentationModel
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel

/**
 * Factory class to generate models for unit tests
 */
object VolumeFactory {
  fun makeVolumePagePresentationModel(size: Int): VolumePagePresentationModel {
    val volumes: MutableList<VolumePresentationModel> = mutableListOf()
    repeat(size) {
      volumes.add(makeVolumePresentationModel())
    }
    return VolumePagePresentationModel(100, volumes)
  }

  fun makeVolumePresentationModel(): VolumePresentationModel {
    return VolumePresentationModel(
        "id",
        "selfLink",
        makeVolumeInfoPresentationModel()
    )
  }

  fun makeVolumeInfoPresentationModel(): VolumeInfoPresentationModel {
    return VolumeInfoPresentationModel(
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
        "canonicalVolumeLink"
    )
  }

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
        "canonicalVolumeLink"
    )
  }
}
