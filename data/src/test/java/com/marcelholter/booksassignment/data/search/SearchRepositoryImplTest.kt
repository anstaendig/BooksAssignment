package com.marcelholter.booksassignment.data.search

import com.marcelholter.booksassignment.data.search.mapper.VolumeInfoMapper
import com.marcelholter.booksassignment.data.search.mapper.VolumeMapper
import com.marcelholter.booksassignment.data.search.mapper.VolumePageMapper
import com.marcelholter.booksassignment.data.source.DataStore
import com.marcelholter.booksassignment.data.source.DataStoreFactory
import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class SearchRepositoryImplTest {
  private lateinit var remoteDataStore: DataStore
  private lateinit var searchRepository: SearchRepository

  @Before
  fun setUp() {
    remoteDataStore = mock()
    searchRepository =
        SearchRepositoryImpl(
            DataStoreFactory(remoteDataStore),
            VolumePageMapper(
                VolumeMapper(VolumeInfoMapper())
            )
        )
  }

  @Test
  fun `Should`() {
    val volumePageData = VolumeFactory.makeVolumePageDataModel(5)
    whenever(remoteDataStore.searchVolumes(any())) doReturn Single.just(volumePageData)
    val volumeDomainData = searchRepository.searchVolumes("queryString")
        .test()
        .assertNoErrors()
        .assertComplete()
        .assertValueCount(1)
        .values()[0]
    assertThat(volumeDomainData.totalVolumes).isEqualTo(volumePageData.totalVolumes)
    assertThat(volumeDomainData.volumes).hasSize(5)
  }
}
