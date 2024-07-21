package com.aurelioklv.kat.core.domain.usecase

import com.aurelioklv.kat.core.domain.model.Breed
import com.aurelioklv.kat.core.data.Resource
import kotlinx.coroutines.flow.Flow

interface BreedUseCase {
    fun getAllBreed(): Flow<Resource<List<Breed>>>

    fun getFavoriteBreed(): Flow<List<Breed>>

    fun setFavoriteBreed(breed: Breed, state: Boolean)
}