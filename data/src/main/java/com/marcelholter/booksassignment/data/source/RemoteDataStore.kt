package com.marcelholter.booksassignment.data.source

import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel
import io.reactivex.Single

/**
 * Interface definition for retrieving data from different data stores
 */
interface RemoteDataStore {
  /**
   * Search volumes
   *
   * Use [startIndex] for pagination.
   *
   * @param[queryString] Search query
   * @param[startIndex] Index of page. Defaults to 0 for first page.
   * @return [Single] that emits [VolumePageDataModel]
   */
  fun searchVolumes(queryString: String, startIndex: Int): Single<VolumePageDataModel>
}
