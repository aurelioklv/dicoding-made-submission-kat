package com.aurelioklv.kat.core.di

import com.aurelioklv.kat.core.data.BreedRepository
import com.aurelioklv.kat.core.data.local.LocalDataSource
import com.aurelioklv.kat.core.data.local.dao.BreedDao
import com.aurelioklv.kat.core.data.remote.RemoteDataSource
import com.aurelioklv.kat.core.data.remote.api.ApiService
import com.aurelioklv.kat.core.domain.repository.IBreedRepository
import com.aurelioklv.kat.core.utils.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(repository: BreedRepository): IBreedRepository

    companion object {
        @Provides
        fun provideAppExecutors(): AppExecutors = AppExecutors()

        @Provides
        fun provideLocalDataSource(breedDao: BreedDao): LocalDataSource =
            LocalDataSource(breedDao)

        @Provides
        fun provideRemoteDataSource(apiService: ApiService): RemoteDataSource =
            RemoteDataSource(apiService)
    }
}