package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.Mapper
import com.marcelholter.booksassignment.data.search.model.VolumeDataModel
import com.marcelholter.booksassignment.domain.search.model.VolumeDomainModel
import javax.inject.Inject

class VolumeMapper
@Inject constructor(
    private val volumeInfoMapper: VolumeInfoMapper
) : Mapper<VolumeDataModel, VolumeDomainModel> {
  override fun mapToDomainModel(dataModel: VolumeDataModel): VolumeDomainModel {
    return VolumeDomainModel(
        dataModel.id,
        dataModel.selfLink,
        volumeInfoMapper.mapToDomainModel(dataModel.volumeInfo)
    )
  }
}
