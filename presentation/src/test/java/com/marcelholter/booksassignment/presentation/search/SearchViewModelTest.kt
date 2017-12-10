package com.marcelholter.booksassignment.presentation.search

import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCase
import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCaseImpl
import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import com.marcelholter.booksassignment.presentation.search.mapper.VolumeMapper
import com.marcelholter.booksassignment.presentation.search.model.VolumePagePresentationModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test

class SearchViewModelTest {
  private lateinit var searchRepository: SearchRepository
  private lateinit var searchVolumesUseCase: SearchVolumesUseCase
  private lateinit var volumeMapper: VolumeMapper
  private lateinit var viewModel: SearchViewModel

  @Before
  fun setUp() {
    searchRepository = mock()
    volumeMapper = VolumeMapper()
    searchVolumesUseCase =
        SearchVolumesUseCaseImpl(searchRepository, Schedulers.trampoline(), Schedulers.trampoline())
    viewModel = SearchViewModel(searchVolumesUseCase, volumeMapper)
  }

  @Test
  fun `OnSearchClicked event should lead to correct view state`() {
    val volumes = VolumeFactory.makeVolumePageDomainModel(5)
    whenever(searchRepository.searchVolumes(any())) doReturn Single.just(volumes)
    val testObserver: TestObserver<SearchViewState> = TestObserver.create()
    viewModel.viewState.subscribe(testObserver)
    viewModel.eventStream.accept(SearchEvent.OnSearchClicked("queryString"))
    testObserver.assertValues(
        SearchViewState(false, false, null, null),
        SearchViewState(true, false, null, null),
        SearchViewState(
            loading = false,
            loadingNextPage = false,
            error = null,
            volumePage = VolumePagePresentationModel(
                volumes.totalVolumes,
                volumes.volumes.map { volumeMapper.mapToPresentationModel(it) }
            )
        )
    )
  }

  @Test
  fun `OnSearchClicked event should lead to correct error view state`() {
    val throwable = Throwable()
    whenever(searchRepository.searchVolumes(any())) doReturn Single.error(throwable)
    val testObserver: TestObserver<SearchViewState> = TestObserver.create()
    viewModel.viewState.subscribe(testObserver)
    viewModel.eventStream.accept(SearchEvent.OnSearchClicked("queryString"))
    testObserver.assertValues(
        SearchViewState(false, false, null, null),
        SearchViewState(true, false, null, null),
        SearchViewState(false, false, throwable, null)
    )
  }
}
