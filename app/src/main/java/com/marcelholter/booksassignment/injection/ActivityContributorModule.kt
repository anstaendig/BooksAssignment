package com.marcelholter.booksassignment.injection

import com.marcelholter.booksassignment.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module to automaticallu generate subcomponents for activity injection
 */
@Module
abstract class ActivityBuilderModule {

  @ContributesAndroidInjector
  abstract fun contributeSearchActivity(): SearchActivity
}
