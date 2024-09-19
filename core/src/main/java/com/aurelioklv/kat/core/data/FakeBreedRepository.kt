package com.aurelioklv.kat.core.data

import com.aurelioklv.kat.core.domain.model.Breed
import com.aurelioklv.kat.core.domain.repository.IBreedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeBreedRepository(val breed: List<Breed>) : IBreedRepository {
    private val breeds = breed
    private val favorites = mutableListOf<Breed>()

    override fun getAllBreed(): Flow<Resource<List<Breed>>> = flow {
        emit(Resource.Success(breeds))
    }

    override fun getBreedById(id: String): Flow<Resource<Breed>> = flow {
        val breed = breeds.find { it.id == id }
        if (breed != null) {
            emit(Resource.Success(breed))
        } else {
            emit(Resource.Error("Breed not found"))
        }
    }

    override fun getFavoriteBreed(): Flow<List<Breed>> = flow {
        emit(favorites)
    }

    override fun setFavoriteBreed(breed: Breed, state: Boolean) {
        if (state) {
            favorites.add(breed)
        } else {
            favorites.remove(breed)
        }
    }
}
