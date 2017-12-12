package com.marcelholter.booksassignment.data.remote

import com.marcelholter.booksassignment.data.search.adapter.VolumeDataJsonAdapter
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Service factory to create retrofit service
 */
object ServiceFactory {
  private const val BASE_URL = "https://www.googleapis.com/books/v1/"

  fun getService(isDebug: Boolean): BooksService {
    return buildService(
        buildOkHttpClient(
            buildLoggingInterceptor(isDebug)
        ),
        buildMoshiConverterFactory()
    )
  }

  private fun buildService(
      okHttpClient: OkHttpClient,
      moshiConverterFactory: MoshiConverterFactory
  ): BooksService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(moshiConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(BooksService::class.java)
  }

  private fun buildOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
  }

  private fun buildMoshiConverterFactory(): MoshiConverterFactory {
    val moshi = Moshi.Builder()
        .add(VolumeDataJsonAdapter())
        .build()
    return MoshiConverterFactory.create(moshi)
  }

  private fun buildLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
    return HttpLoggingInterceptor()
        .setLevel(
            if (isDebug) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        )
  }
}
