package com.marcelholter.booksassignment.data

/**
 * Interface definition for data to domain model mapping.
 */
interface Mapper<in T, out D> {
    fun mapToDomainModel(dataModel: T): D
}
