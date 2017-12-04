package com.marcelholter.booksassignment.data.remote

import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit interface for api endpoints
 */
interface BooksService {
  /**
   * Search for volumes based on query string
   *
   * @param[queryString]
   * @return [Single] that emits [VolumePageDataModel]
   */
  @GET("volumes")
  fun searchVolumes(@Query("q") queryString: String): Single<VolumePageDataModel>
}
