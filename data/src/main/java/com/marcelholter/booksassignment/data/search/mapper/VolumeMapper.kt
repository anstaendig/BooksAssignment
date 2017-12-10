package com.marcelholter.booksassignment.data.search.mapper

import com.marcelholter.booksassignment.data.Mapper
import com.marcelholter.booksassignment.data.search.model.VolumeDataModel
import com.marcelholter.booksassignment.domain.search.model.ImageLinksDomainModel
import com.marcelholter.booksassignment.domain.search.model.VolumeDomainModel
import javax.inject.Inject

class VolumeMapper
@Inject constructor() : Mapper<VolumeDataModel, VolumeDomainModel> {
  override fun mapToDomainModel(dataModel: VolumeDataModel): VolumeDomainModel {
    return VolumeDomainModel(
        dataModel.id,
        dataModel.title,
        dataModel.subtitle ?: "No subtitle",
        dataModel.authors ?: emptyList(),
        dataModel.publisher ?: "No publisher found",
        dataModel.publishedDate ?: "No published date found",
        dataModel.description ?: "No description",
        dataModel.pageCount ?: 0,
        dataModel.mainCategory ?: "No main category",
        dataModel.categories ?: emptyList(),
        dataModel.averageRating ?: 0F,
        dataModel.ratingsCount ?: 0,
        ImageLinksDomainModel(
            dataModel.imageLinks?.thumbnail,
            dataModel.imageLinks?.small,
            dataModel.imageLinks?.medium,
            dataModel.imageLinks?.large,
            dataModel.imageLinks?.smallThumbnail,
            dataModel.imageLinks?.extraLarge
        ),
        dataModel.language ?: "No language found",
        dataModel.previewLink,
        dataModel.canonicalVolumeLink,
        dataModel.searchTextSnippet ?: "No search snippet found"
    )
  }
}
