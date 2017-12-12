package com.marcelholter.booksassignment.data.source

import javax.inject.Inject

/**
 * Factory to retrieve different data stores.
 */
class DataStoreFactory
@Inject constructor(val remoteDataStore: RemoteDataStore)
