package com.marcelholter.booksassignment.injection

import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCase
import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Named

@Module
abstract class DomainModule {
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
  }

  @Binds
  abstract fun bindSearchVolumesUseCase(searchVolumesUseCaseImpl: SearchVolumesUseCaseImpl)
      : SearchVolumesUseCase
}
