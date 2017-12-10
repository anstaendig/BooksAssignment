package com.marcelholter.booksassignment.domain.search

import com.marcelholter.booksassignment.domain.search.model.ImageLinksDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumeDomainModel
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
