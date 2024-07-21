package com.aurelioklv.kat.core.data

import com.aurelioklv.kat.core.domain.model.Breed
import com.aurelioklv.kat.core.data.local.LocalDataSource
import com.aurelioklv.kat.core.data.remote.RemoteDataSource
import com.aurelioklv.kat.core.data.remote.api.ApiResponse
import com.aurelioklv.kat.core.data.remote.response.NetworkBreed
import com.aurelioklv.kat.core.domain.repository.IBreedRepository
import com.aurelioklv.kat.core.utils.AppExecutors
import com.aurelioklv.kat.core.utils.mapper.BreedEntityToModelMapper
import com.aurelioklv.kat.core.utils.mapper.BreedModelToEntityMapper
import com.aurelioklv.kat.core.utils.mapper.BreedNetworkToEntityMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BreedRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IBreedRepository {

    override fun getAllBreed(): Flow<Resource<List<Breed>>> =
        object : NetworkBoundResource<List<Breed>, List<NetworkBreed>>() {
            override fun loadFromDB(): Flow<List<Breed>> {
                return localDataSource.getAllBreed()
                    .map { it.map { breedEntity -> BreedEntityToModelMapper.map(breedEntity) } }
            }

            override fun shouldFetch(data: List<Breed>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<NetworkBreed>>> =
                remoteDataSource.getAllBreed()

            override suspend fun saveCallResult(data: List<NetworkBreed>) {
                val breedEntities = data.map { BreedNetworkToEntityMapper.map(it) }
                localDataSource.insertBreed(breedEntities)
            }
        }.asFlow()

    override fun getFavoriteBreed(): Flow<List<Breed>> {
        return localDataSource.getFavoriteBreed()
            .map { it.map { entity -> BreedEntityToModelMapper.map(entity) } }
    }

    override fun setFavoriteBreed(breed: Breed, state: Boolean) {
        val breedEntity = BreedModelToEntityMapper.map(breed)
        appExecutors.diskIO().execute { localDataSource.setFavoriteBreed(breedEntity, state) }
    }
}

