package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.search.VolumeFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class VolumeInfoMapperTest {
  @Test
  fun `Should transform data to domain model successfully`() {
    val volumeInfoData = VolumeFactory.makeVolumeInfoDataModel()
    val volumeInfoDomain = VolumeInfoMapper().mapToDomainModel(volumeInfoData)
    assertThat(volumeInfoData.title).isEqualTo(volumeInfoDomain.title)
    assertThat(volumeInfoData.subtitle).isEqualTo(volumeInfoDomain.subtitle)
    assertThat(volumeInfoData.authors).isEqualTo(volumeInfoDomain.authors)
    assertThat(volumeInfoData.publisher).isEqualTo(volumeInfoDomain.publisher)
    assertThat(volumeInfoData.publishedDate).isEqualTo(volumeInfoDomain.publishedDate)
    assertThat(volumeInfoData.descriptions).isEqualTo(volumeInfoDomain.descriptions)
    assertThat(volumeInfoData.pageCount).isEqualTo(volumeInfoDomain.pageCount)
    assertThat(volumeInfoData.printedPageCount).isEqualTo(volumeInfoDomain.printedPageCount)
    assertThat(volumeInfoData.categories).isEqualTo(volumeInfoDomain.categories)
    assertThat(volumeInfoData.maturityRating).isEqualTo(volumeInfoDomain.maturityRating)
    assertThat(volumeInfoData.imageLink).isEqualTo(volumeInfoDomain.imageLink)
    assertThat(volumeInfoData.language).isEqualTo(volumeInfoDomain.language)
    assertThat(volumeInfoData.previewLink).isEqualTo(volumeInfoDomain.previewLink)
    assertThat(volumeInfoData.infoLink).isEqualTo(volumeInfoDomain.infoLink)
    assertThat(volumeInfoData.canonialVolumeLink).isEqualTo(volumeInfoDomain.canonialVolumeLink)
  }
}
