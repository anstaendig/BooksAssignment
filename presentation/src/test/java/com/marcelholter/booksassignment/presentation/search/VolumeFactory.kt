package com.marcelholter.booksassignment.presentation.search

import com.marcelholter.booksassignment.domain.search.model.ImageLinksDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumeDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import com.marcelholter.booksassignment.presentation.search.model.ImageLinksPresentationModel
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
        ImageLinksPresentationModel(
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
        ImageLinksDomainModel(
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
