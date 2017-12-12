package com.marcelholter.booksassignment.presentation.search

import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCase
import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCaseImpl
import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import com.marcelholter.booksassignment.presentation.search.mapper.VolumeMapper
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

class SearchViewModelTest {
  private lateinit var searchRepository: SearchRepository
  private lateinit var searchVolumesUseCase: SearchVolumesUseCase
  private lateinit var volumeMapper: VolumeMapper
  private lateinit var viewModel: SearchViewModel
  private lateinit var statesObserver: TestObserver<SearchViewState>

  @Before
  fun setUp() {
    searchRepository = mock()
    volumeMapper = VolumeMapper()
    searchVolumesUseCase =
        SearchVolumesUseCaseImpl(searchRepository, Schedulers.trampoline(), Schedulers.trampoline())
    viewModel = SearchViewModel(searchVolumesUseCase, volumeMapper)
    statesObserver = viewModel.viewStates.test()
  }

  @Test
  fun `OnSearchClicked event should lead to correct view state`() {
    val volumes = VolumeFactory.makeVolumePageDomainModel(5)
    whenever(searchRepository.searchVolumes(any(), any())) doReturn Single.just(volumes)
    viewModel.processEvents(Observable.just(SearchEvent.OnSearchClicked("queryString")))
    statesObserver.assertValues(
        SearchViewState(false, false, null, 0, emptyList()),
        SearchViewState(true, false, null, 0, emptyList()),
        SearchViewState(
            loading = false,
            loadingNextPage = false,
            error = null,
            totalVolumes = 100,
            volumes = volumes.volumes.map { volumeMapper.mapToPresentationModel(it) }
        )
    )
  }

  @Test
  fun `OnSearchClicked event should lead to correct error view state`() {
    val throwable = Throwable()
    whenever(searchRepository.searchVolumes(any(), any())) doReturn Single.error(throwable)
    viewModel.processEvents(Observable.just(SearchEvent.OnSearchClicked("queryString")))
    statesObserver.assertValues(
        SearchViewState(false, false, null, 0, emptyList()),
        SearchViewState(true, false, null, 0, emptyList()),
        SearchViewState(false, false, throwable, 0, emptyList())
    )
  }
}
