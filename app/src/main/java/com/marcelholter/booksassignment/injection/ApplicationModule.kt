package com.marcelholter.booksassignment.injection

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named

/**
 * Module to provide application level dependencies
 */
@Module
object ApplicationModule {
  @Provides
  @JvmStatic
  @Named("mainThread")
  fun provideMainThread(): Scheduler = AndroidSchedulers.mainThread()
}
