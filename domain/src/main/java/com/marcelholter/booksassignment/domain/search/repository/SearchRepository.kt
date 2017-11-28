package com.marcelholter.booksassignment.domain.search.repository

import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import io.reactivex.Single

/**
 * Interface definition for communication with the data layer.
 */
interface SearchRepository {

  fun searchBooks(queryString: String): Single<VolumePageDomainModel>
}
