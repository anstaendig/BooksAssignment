package com.marcelholter.booksassignment.data.source

import com.marcelholter.booksassignment.data.remote.BooksService
import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Remote implementation of [DataStore] that fetches data from [BooksService]
 */
class RemoteDataStoreImpl
@Inject constructor(
    private val booksService: BooksService
) : DataStore {
  override fun searchVolumes(queryString: String): Single<VolumePageDataModel> {
    return booksService.searchVolumes(queryString)
  }
}
