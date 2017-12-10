package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.Mapper
import com.marcelholter.booksassignment.data.search.model.VolumeDataModel
import com.marcelholter.booksassignment.data.search.model.VolumePageDataModel
import com.marcelholter.booksassignment.domain.search.model.VolumePageDomainModel
import javax.inject.Inject

class VolumePageMapper
@Inject constructor(
    private val volumeMapper: VolumeMapper
) : Mapper<VolumePageDataModel, VolumePageDomainModel> {
  override fun mapToDomainModel(dataModel: VolumePageDataModel): VolumePageDomainModel {
    return VolumePageDomainModel(
        dataModel.totalItems,
        dataModel.items.map { volumeDataModel: VolumeDataModel ->
          volumeMapper.mapToDomainModel(volumeDataModel)
        }
    )
  }
}
