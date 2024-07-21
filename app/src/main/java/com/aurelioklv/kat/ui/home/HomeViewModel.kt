package com.aurelioklv.kat.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aurelioklv.kat.core.domain.usecase.BreedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(breedUseCase: BreedUseCase) : ViewModel() {
    val breeds = breedUseCase.getAllBreed().asLiveData()
}