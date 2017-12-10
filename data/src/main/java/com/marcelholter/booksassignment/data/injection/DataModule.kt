package com.marcelholter.booksassignment.data.injection

import com.marcelholter.booksassignment.data.remote.BooksService
import com.marcelholter.booksassignment.data.remote.RemoteDataStoreImpl
import com.marcelholter.booksassignment.data.remote.ServiceFactory
import com.marcelholter.booksassignment.data.search.SearchRepositoryImpl
import com.marcelholter.booksassignment.data.source.RemoteDataStore
import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

/**
 * Module to provide data dependencies.
 */
@Module
abstract class DataModule {

  /**
   * This is needed to provide dependencies statically.
   */
  @Module
  companion object {
    @Provides
    @JvmStatic
    @Named("io")
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @JvmStatic
    @Named("computation")
    fun provideComputationScheduler(): Scheduler = Schedulers.computation()

    @Provides
    @JvmStatic
    fun provideBooksService(): BooksService = ServiceFactory.getService(true)
  }

  @Binds
  abstract fun bindRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository

  @Binds
  abstract fun bindRemoteDataStore(remoteDataStoreImpl: RemoteDataStoreImpl): RemoteDataStore
}
