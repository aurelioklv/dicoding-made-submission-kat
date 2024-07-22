package com.aurelioklv.kat.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aurelioklv.kat.core.data.Resource
import com.aurelioklv.kat.core.domain.model.Breed
import com.aurelioklv.kat.core.domain.usecase.BreedUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val breedUseCase: BreedUseCase) : ViewModel() {
    private val _breedDetail = MutableLiveData<Resource<Breed>>()
    val breedDetail: LiveData<Resource<Breed>> get() = _breedDetail

    fun getBreedById(id: String) {
        viewModelScope.launch {
            breedUseCase.getBreedById(id).collect { breed ->
                _breedDetail.postValue(breed)
            }
        }
    }

    fun setFavoriteBreed(breed: Breed, newStatus: Boolean) =
        breedUseCase.setFavoriteBreed(breed, newStatus)
}