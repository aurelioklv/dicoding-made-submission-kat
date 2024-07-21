package com.aurelioklv.kat.di

import com.aurelioklv.kat.core.domain.usecase.BreedInteractor
import com.aurelioklv.kat.core.domain.usecase.BreedUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideBreedUseCase(breedUseCase: BreedInteractor): BreedUseCase
}