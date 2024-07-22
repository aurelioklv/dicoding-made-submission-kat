package com.aurelioklv.kat.di

import com.aurelioklv.kat.core.domain.usecase.BreedInteractor
import com.aurelioklv.kat.core.domain.usecase.BreedUseCase
import com.aurelioklv.kat.ui.detail.DetailViewModel
import com.aurelioklv.kat.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<BreedUseCase> { BreedInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}