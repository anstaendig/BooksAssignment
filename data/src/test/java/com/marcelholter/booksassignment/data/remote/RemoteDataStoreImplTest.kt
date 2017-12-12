package com.marcelholter.booksassignment.data.remote

import com.marcelholter.booksassignment.data.search.VolumeFactory
import com.marcelholter.booksassignment.data.source.RemoteDataStore
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class RemoteDataStoreImplTest {
  private lateinit var booksService: BooksService
  private lateinit var remoteDataStore: RemoteDataStore

  @Before
  fun setUp() {
    booksService = mock()
    remoteDataStore = RemoteDataStoreImpl(booksService)
  }

  @Test
  fun `Should call service method with correct parameters`() {
    val volumePageData = VolumeFactory.makeVolumePageDataModel(5)
    whenever(booksService.searchVolumes(any(), any(), any())) doReturn Single.just(volumePageData)
    val queryStringArgumentCaptor = argumentCaptor<String>()
    val startIndexArgumentCaptor = argumentCaptor<Int>()
    val pageSizeArgumentCaptor = argumentCaptor<Int>()
    remoteDataStore.searchVolumes("queryString", 0)
        .test()
        .assertNoErrors()
        .assertComplete()
        .assertValue(volumePageData)
    verify(booksService).searchVolumes(
        queryStringArgumentCaptor.capture(),
        startIndexArgumentCaptor.capture(),
        pageSizeArgumentCaptor.capture()
    )
    assertThat(queryStringArgumentCaptor.firstValue).isEqualTo("queryString")
    assertThat(startIndexArgumentCaptor.firstValue).isEqualTo(0)
    assertThat(pageSizeArgumentCaptor.firstValue).isEqualTo(20)
  }
}
