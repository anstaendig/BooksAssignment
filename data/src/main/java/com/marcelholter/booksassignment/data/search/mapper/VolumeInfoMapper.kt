package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.Mapper
import com.marcelholter.booksassignment.data.search.model.VolumeInfoDataModel
import com.marcelholter.booksassignment.domain.search.model.VolumeInfoDomainModel
import javax.inject.Inject

class VolumeInfoMapper
@Inject constructor() : Mapper<VolumeInfoDataModel, VolumeInfoDomainModel> {
  override fun mapToDomainModel(dataModel: VolumeInfoDataModel): VolumeInfoDomainModel {
    return VolumeInfoDomainModel(
        dataModel.title,
        dataModel.subtitle,
        dataModel.authors,
        dataModel.publisher,
        dataModel.publishedDate,
        dataModel.descriptions,
        dataModel.pageCount,
        dataModel.printedPageCount,
        dataModel.categories,
        dataModel.maturityRating,
        dataModel.imageLink,
        dataModel.language,
        dataModel.previewLink,
        dataModel.infoLink,
        dataModel.canonialVolumeLink
    )
  }
}
