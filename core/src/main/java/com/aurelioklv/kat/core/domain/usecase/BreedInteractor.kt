package com.aurelioklv.kat.core.domain.usecase

import com.aurelioklv.kat.core.data.Resource
import com.aurelioklv.kat.core.domain.model.Breed
import com.aurelioklv.kat.core.domain.repository.IBreedRepository
import kotlinx.coroutines.flow.Flow

class BreedInteractor(private val breedRepository: IBreedRepository) :
    BreedUseCase {
    override fun getAllBreed(): Flow<Resource<List<Breed>>> =
        breedRepository.getAllBreed()

    override fun getBreedById(id: String): Flow<Resource<Breed>> =
        breedRepository.getBreedById(id)

    override fun getFavoriteBreed(): Flow<List<Breed>> =
        breedRepository.getFavoriteBreed()

    override fun setFavoriteBreed(breed: Breed, state: Boolean) =
        breedRepository.setFavoriteBreed(breed, state)
}