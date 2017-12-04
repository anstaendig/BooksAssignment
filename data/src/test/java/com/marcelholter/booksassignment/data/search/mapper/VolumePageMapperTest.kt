package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.search.VolumeFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class VolumePageMapperTest {
  @Test
  fun `Should map data to domain model successfully`() {
    val volumePageData = VolumeFactory.makeVolumePageDataModel(5)
    val volumePageDomain =
        VolumePageMapper(VolumeMapper(VolumeInfoMapper())).mapToDomainModel(volumePageData)
    assertThat(volumePageData.totalVolumes).isEqualTo(volumePageDomain.totalVolumes)
    assertThat(volumePageDomain.volumes).hasSize(5)
  }
}
