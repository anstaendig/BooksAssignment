package com.marcelholter.booksassignment.data.search

import com.marcelholter.booksassignment.data.search.mapper.VolumeMapper
import com.marcelholter.booksassignment.data.search.mapper.VolumePageMapper
import com.marcelholter.booksassignment.data.source.DataStoreFactory
import com.marcelholter.booksassignment.data.source.RemoteDataStore
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
  private lateinit var remoteDataStore: RemoteDataStore
  private lateinit var searchRepository: SearchRepository

  @Before
  fun setUp() {
    remoteDataStore = mock()
    searchRepository =
        SearchRepositoryImpl(
            DataStoreFactory(remoteDataStore),
            VolumePageMapper(VolumeMapper())
        )
  }

  @Test
  fun `Should call remote data store and map data successfully`() {
    val volumePageData = VolumeFactory.makeVolumePageDataModel(5)
    whenever(remoteDataStore.searchVolumes(any(), any())) doReturn Single.just(volumePageData)
    val volumeDomainData = searchRepository.searchVolumes("queryString")
        .test()
        .assertNoErrors()
        .assertComplete()
        .assertValueCount(1)
        .values()[0]
    assertThat(volumeDomainData.totalVolumes).isEqualTo(volumePageData.totalItems)
    assertThat(volumeDomainData.volumes).hasSize(5)
  }

  @Test
  fun `Should call remote data store and propagate error correctly`() {
    val throwable = Throwable()
    whenever(remoteDataStore.searchVolumes(any(), any())) doReturn Single.error(throwable)
    searchRepository.searchVolumes("queryString")
        .test()
        .assertError(throwable)
  }
}
