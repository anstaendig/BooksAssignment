package com.marcelholter.booksassignment.domain.injection

import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCase
import com.marcelholter.booksassignment.domain.search.SearchVolumesUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DomainModule {

  @Binds
  abstract fun bindSearchVolumesUseCase(searchVolumesUseCaseImpl: SearchVolumesUseCaseImpl)
      : SearchVolumesUseCase
}
