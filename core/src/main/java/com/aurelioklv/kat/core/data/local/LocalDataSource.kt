package com.aurelioklv.kat.core.data.local

import com.aurelioklv.kat.core.data.local.dao.BreedDao
import com.aurelioklv.kat.core.data.local.entity.BreedEntity
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val breedDao: BreedDao) {

    fun getAllBreed(): Flow<List<BreedEntity>> = breedDao.getAllBreed()

    fun getFavoriteBreed(): Flow<List<BreedEntity>> = breedDao.getFavoriteBreed()

    suspend fun insertBreed(breeds: List<BreedEntity>) =
        breedDao.insertBreed(breeds)

    fun setFavoriteBreed(breed: BreedEntity, newState: Boolean) {
        breed.isFavorite = newState
        breedDao.updateFavoriteBreed(breed)
    }

    fun getBreedById(id: String): Flow<BreedEntity> = breedDao.getBreedById(id)
}