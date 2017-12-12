package com.marcelholter.booksassignment.search

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.support.v7.widget.SearchView.OnQueryTextListener
import android.view.Menu
import android.widget.ProgressBar
import android.widget.Toast
import com.jakewharton.rxrelay2.PublishRelay
import com.marcelholter.booksassignment.R
import com.marcelholter.booksassignment.base.BaseView
import com.marcelholter.booksassignment.presentation.search.SearchEvent
import com.marcelholter.booksassignment.presentation.search.SearchEvent.OnSearchClicked
import com.marcelholter.booksassignment.presentation.search.SearchViewModel
import com.marcelholter.booksassignment.presentation.search.SearchViewState
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel
import com.marcelholter.booksassignment.util.bindView
import com.marcelholter.booksassignment.util.go
import com.marcelholter.booksassignment.util.navigateToVolumeDetail
import com.marcelholter.booksassignment.util.show
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.LazyThreadSafetyMode.NONE

/**
 * Activity to search for books
 */
class SearchActivity
  : BaseView<SearchEvent, SearchViewState>, DaggerAppCompatActivity() {
  private val recyclerView: RecyclerView by bindView(R.id.a_search_recycler_view)
  private val progressBar: ProgressBar by bindView(R.id.a_search_progress_bar)

  @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
  private lateinit var viewModel: SearchViewModel

  private val disposables = CompositeDisposable()

  // SearchAdapter initialisation. Pass in function for click listener
  private val searchAdapter: SearchAdapter by lazy(NONE) {
    SearchAdapter { volume, view ->
      navigateToVolumeDetail(volume, view)
    }
  }

  // LayoutManager initialisation
  private val layoutManager: LinearLayoutManager by lazy(NONE) {
    LinearLayoutManager(this)
  }

  // Scroll listener initialisation for pagination
  private val onScrollListener: PaginationRecyclerViewOnScrollListener =
      PaginationRecyclerViewOnScrollListener(layoutManager) { startIndex ->
        onLoadMoreEvent.accept(SearchEvent.OnLoadMore(startIndex))
      }

  private val onSearchClickedEvent: PublishRelay<SearchEvent.OnSearchClicked> = PublishRelay.create()
  private val onLoadMoreEvent: PublishRelay<SearchEvent.OnLoadMore> = PublishRelay.create()

  override val events: Observable<SearchEvent> =
      Observable.merge(
          onSearchClickedEvent.distinctUntilChanged(),
          onLoadMoreEvent
      )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.a_search)
    // Get ViewModel from provider. Provider will return same ViewModel instance after
    // configuration change
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel::class.java)
    bindToViewModel()
    // Setup RecyclerView
    with(recyclerView) {
      layoutManager = this@SearchActivity.layoutManager
      adapter = searchAdapter
      addOnScrollListener(onScrollListener)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    menuInflater.inflate(R.menu.a_search_menu, menu)
    val searchView: SearchView = menu.findItem(R.id.a_search_menu_search).actionView as SearchView
    searchView.setOnQueryTextListener(object : OnQueryTextListener {
      override fun onQueryTextSubmit(query: String): Boolean {
        onSearchClickedEvent.accept(OnSearchClicked(query))
        onScrollListener.resetState()
        return true
      }

      override fun onQueryTextChange(newText: String): Boolean {
        return false
      }
    })
    return true
  }

  override fun onDestroy() {
    disposables.dispose()
    super.onDestroy()
  }

  /**
   * Render view state
   */
  override fun render(viewState: SearchViewState) {
    showLoading(viewState.loading)
    showError(viewState.error)
    showVolumes(viewState.volumes, viewState.totalVolumes)
  }

  /**
   * Subscribe to view states from view model and bind event stream to view model
   */
  private fun bindToViewModel() {
    disposables.add(viewModel.viewStates.subscribe(this::render))
    viewModel.processEvents(events)
  }

  private fun showLoading(loading: Boolean) {
    if (loading) {
      progressBar.show()
    } else {
      progressBar.go()
    }
  }

  private fun showError(error: Throwable?) {
    error?.let {
      Toast.makeText(this, it.localizedMessage, Toast.LENGTH_LONG).show()
    }
  }

  private fun showVolumes(
      volumes: List<VolumePresentationModel>,
      totalVolumes: Int
  ) {
    onScrollListener.totalItemCount = totalVolumes
    searchAdapter.setVolumes(volumes)
    recyclerView.show()
  }
}
