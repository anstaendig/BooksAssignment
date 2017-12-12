package com.marcelholter.booksassignment.injection

import com.marcelholter.booksassignment.BooksApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Component to create application dependency graph including all necessary modules
 */
@Singleton
@Component(modules = arrayOf(
    ApplicationModule::class,
    AndroidSupportInjectionModule::class,
    ActivityBuilderModule::class,
    DomainModule::class,
    DataModule::class,
    PresentationModule::class
))
interface ApplicationComponent : AndroidInjector<BooksApplication> {

  @Component.Builder
  abstract class Builder : AndroidInjector.Builder<BooksApplication>()
}
