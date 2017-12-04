package com.marcelholter.booksassignment.data.source

import javax.inject.Inject

/**
 * Factory to retrieve different data stores.
 */
class DataStoreFactory
@Inject constructor(
    private val remoteDataStore: RemoteDataStoreImpl
) {
  fun getRemoteDataStore(): DataStore {
    return remoteDataStore
  }
}
