package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.search.VolumeFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class VolumeMapperTest {
  @Test
  fun `Should map data to domain model successfully`() {
    val volumeDataModel = VolumeFactory.makeVolumeDataModel()
    val volumeDomainModel = VolumeMapper().mapToDomainModel(volumeDataModel)
    assertThat(volumeDomainModel.id).isEqualTo(volumeDataModel.id)
    assertThat(volumeDomainModel.title).isEqualTo(volumeDataModel.title)
    assertThat(volumeDomainModel.subtitle).isEqualTo(volumeDataModel.subtitle)
    assertThat(volumeDomainModel.authors).isEqualTo(volumeDataModel.authors)
    assertThat(volumeDomainModel.publisher).isEqualTo(volumeDataModel.publisher)
    assertThat(volumeDomainModel.publishedDate).isEqualTo(volumeDataModel.publishedDate)
    assertThat(volumeDomainModel.description).isEqualTo(volumeDataModel.description)
    assertThat(volumeDomainModel.pageCount).isEqualTo(volumeDataModel.pageCount)
    assertThat(volumeDomainModel.mainCategory).isEqualTo(volumeDataModel.mainCategory)
    assertThat(volumeDomainModel.categories).isEqualTo(volumeDataModel.categories)
    assertThat(volumeDomainModel.averageRating).isEqualTo(volumeDataModel.averageRating)
    assertThat(volumeDomainModel.ratingsCount).isEqualTo(volumeDataModel.ratingsCount)
    assertThat(volumeDomainModel.language).isEqualTo(volumeDataModel.language)
    assertThat(volumeDomainModel.previewLink).isEqualTo(volumeDataModel.previewLink)
    assertThat(volumeDomainModel.canonicalVolumeLink).isEqualTo(volumeDataModel.canonicalVolumeLink)
    assertThat(volumeDomainModel.searchTextSnippet).isEqualTo(volumeDataModel.searchTextSnippet)
  }
}
