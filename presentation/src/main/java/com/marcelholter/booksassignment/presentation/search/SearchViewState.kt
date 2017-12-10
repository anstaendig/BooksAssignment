package com.marcelholter.booksassignment.presentation.search

import com.marcelholter.booksassignment.presentation.base.BaseViewState
import com.marcelholter.booksassignment.presentation.search.model.VolumePagePresentationModel
import com.marcelholter.booksassignment.presentation.search.model.VolumePresentationModel

data class SearchViewState(
    val loading: Boolean,
    val loadingNextPage: Boolean,
    val error: Throwable?,
    val volumePage: VolumePagePresentationModel?
) : BaseViewState
