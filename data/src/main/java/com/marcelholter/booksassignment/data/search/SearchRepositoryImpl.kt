package com.marcelholter.booksassignment.data.search

import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import io.reactivex.Single

class SearchRepositoryImpl : SearchRepository {
  override fun searchVolumes(queryString: String): Single<VolumePageDomainModel> {

  }

}
