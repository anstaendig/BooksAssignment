package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.search.VolumeFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class VolumeMapperTest {
  @Test
  fun `Should map data to domain model successfully`() {
    val volumeData = VolumeFactory.makeVolumeDataModel()
    val volumeDomain = VolumeMapper(VolumeInfoMapper()).mapToDomainModel(volumeData)
    assertThat(volumeData.id).isEqualTo(volumeDomain.id)
    assertThat(volumeData.selfLink).isEqualTo(volumeDomain.selfLink)
    assertThat(volumeDomain.volumeInfo).isNotNull()
  }
}
