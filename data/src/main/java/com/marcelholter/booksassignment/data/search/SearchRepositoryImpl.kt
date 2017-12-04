package com.marcelholter.booksassignment.data.search

import com.marcelholter.booksassignment.data.search.mapper.VolumePageMapper
import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel
import com.marcelholter.booksassignment.data.source.DataStoreFactory
import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import com.marcelholter.booksassignment.domain.search.repository.SearchRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Implementation of [SearchRepository] on domain layer to fetch data from data stores.
 */
class SearchRepositoryImpl
@Inject constructor(
    private val dataStoreFactory: DataStoreFactory,
    private val volumePageMapper: VolumePageMapper
) : SearchRepository {
  override fun searchVolumes(queryString: String): Single<VolumePageDomainModel> {
    return dataStoreFactory.getRemoteDataStore().searchVolumes(queryString)
        .map { volumePage: VolumePageDataModel ->
          volumePageMapper.mapToDomainModel(volumePage)
        }
  }
}
