package com.aurelioklv.kat.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aurelioklv.kat.core.domain.usecase.BreedUseCase

class FavoriteViewModel(private val breedUseCase: BreedUseCase) : ViewModel() {
    val favoriteBreeds = breedUseCase.getFavoriteBreed().asLiveData()
}