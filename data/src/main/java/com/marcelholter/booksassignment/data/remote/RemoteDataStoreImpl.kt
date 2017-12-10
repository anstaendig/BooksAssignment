package com.marcelholter.booksassignment.data.remote

import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel
import com.marcelholter.booksassignment.data.source.RemoteDataStore
import io.reactivex.Single
import javax.inject.Inject

private const val DEFAULT_PAGE_SIZE = 20

/**
 * Implementation of [RemoteDataStore] that fetches data from [BooksService]
 */
class RemoteDataStoreImpl
@Inject constructor(
    private val booksService: BooksService
) : RemoteDataStore {
  override fun searchVolumes(queryString: String, startIndex: Int): Single<VolumePageDataModel> {
    return booksService.searchVolumes(queryString, startIndex, DEFAULT_PAGE_SIZE)
  }
}
