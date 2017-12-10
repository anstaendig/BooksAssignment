package com.marcelholter.booksassignment.presentation.search.mapper

import com.marcelholter.booksassignment.domain.search.model.VolumeDomainModel
import com.marcelholter.booksassignment.presentation.Mapper
import com.marcelholter.booksassignment.presentation.search.model.ImageLinksPresentationModel
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel
import javax.inject.Inject

class VolumeMapper
@Inject constructor() : Mapper<VolumeDomainModel, VolumePresentationModel> {
  override fun mapToPresentationModel(domainModel: VolumeDomainModel): VolumePresentationModel {
    return VolumePresentationModel(
        domainModel.id,
        domainModel.title,
        domainModel.subtitle,
        domainModel.authors,
        domainModel.publisher,
        domainModel.publishedDate,
        domainModel.description,
        domainModel.pageCount,
        domainModel.mainCategory,
        domainModel.categories,
        domainModel.averageRating,
        domainModel.ratingsCount,
        ImageLinksPresentationModel(
            domainModel.imageLinks.thumbnail,
            domainModel.imageLinks.small,
            domainModel.imageLinks.medium,
            domainModel.imageLinks.large,
            domainModel.imageLinks.smallThumbnail,
            domainModel.imageLinks.extraLarge
        ),
        domainModel.language,
        domainModel.previewLink,
        domainModel.canonicalVolumeLink,
        domainModel.searchTextSnippet
    )
  }
}
