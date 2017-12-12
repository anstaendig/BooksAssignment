package com.marcelholter.booksassignment.domain.search

import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class SearchVolumesUseCaseImplTest {
  private lateinit var repository: SearchRepository
  private lateinit var useCase: SearchVolumesUseCase

  @Before
  fun setUp() {
    repository = mock()
    useCase = SearchVolumesUseCaseImpl(repository, Schedulers.trampoline(), Schedulers.trampoline())
  }

  @Test
  fun `Stream should emit InFlight and Success result based on SearchVolumes action success`() {
    val volumes = VolumeFactory.makeVolumePageDomainModel(5)
    whenever(repository.searchVolumes(any(), any())) doReturn Single.just(volumes)
    val testObserver: TestObserver<SearchResult.SearchVolumesResult> = TestObserver.create()
    useCase.searchVolumesResult
        .apply(Observable.just(SearchAction.SearchVolumesAction("queryString")))
        .subscribe(testObserver)
    testObserver
        .assertNoErrors()
        .assertValueCount(2)
        .assertValues(
            SearchResult.SearchVolumesResult.InFlight,
            SearchResult.SearchVolumesResult.Success(volumes)
        )
  }

  @Test
  fun `Stream should emit InFlight and Failure result based on SearchVolumes action failure`() {
    val throwable = Throwable()
    whenever(repository.searchVolumes(any(), any())) doReturn Single.error(throwable)
    val testObserver: TestObserver<SearchResult.SearchVolumesResult> = TestObserver.create()
    useCase.searchVolumesResult
        .apply(Observable.just(SearchAction.SearchVolumesAction("queryString")))
        .subscribe(testObserver)
    testObserver
        .assertNoErrors()
        .assertValueCount(2)
        .assertValues(
            SearchResult.SearchVolumesResult.InFlight,
            SearchResult.SearchVolumesResult.Failure(throwable)
        )
  }
}
