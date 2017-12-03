package com.marcelholter.booksassignment.domain.search.repository

import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import io.reactivex.Single

/**
 * Interface definition for communication with the data layer.
 */
interface SearchRepository {
  /**
   * Search for books based on a string
   *
   * Data is wrapped in [VolumePageDomainModel] for pagination.
   *
   * @param[queryString] Query string
   * @return [Single] that emits [VolumePageDomainModel]
   */
  fun searchVolumes(queryString: String): Single<VolumePageDomainModel>
}
