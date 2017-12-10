package com.marcelholter.booksassignment.domain.search.repository

import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import io.reactivex.Single

/**
 * Interface definition for communication with the data layer.
 */
interface SearchRepository {
  /**
   * Search for books based on a string.
   *
   * Use [startIndex] for pagination. Maps data to domain model. Data is wrapped in
   * [VolumePageDomainModel] for pagination.
   *
   * @param[queryString] Query string
   * @param[startIndex] Index for page. Default 0 for first page
   * @return [Single] that emits [VolumePageDomainModel]
   */
  fun searchVolumes(queryString: String, startIndex: Int = 0): Single<VolumePageDomainModel>
}
