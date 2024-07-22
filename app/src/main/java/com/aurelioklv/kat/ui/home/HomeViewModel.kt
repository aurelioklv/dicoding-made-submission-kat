package com.aurelioklv.kat.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aurelioklv.kat.core.domain.usecase.BreedUseCase

class HomeViewModel(breedUseCase: BreedUseCase) : ViewModel() {
    val breeds = breedUseCase.getAllBreed().asLiveData()
}