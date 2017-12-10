package com.marcelholter.booksassignment.presentation

/**
 * Interface definition for mappers from domain to presentation model
 */
interface Mapper<in D, out P> {
  fun mapToPresentationModel(domainModel: D): P
}
