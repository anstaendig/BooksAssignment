package com.marcelholter.booksassignment.data.source

import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel
import io.reactivex.Single

/**
 * Interface definition for retrieving data from different data stores
 */
interface DataStore {
  /**
   * Search volumes
   *
   * @param[queryString] Search query
   * @return [Single] that emits [VolumePageDataModel]
   */
  fun searchVolumes(queryString: String): Single<VolumePageDataModel>
}
