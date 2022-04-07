package com.akbararyo.ghibliapps.di

import com.akbararyo.ghibliapps.core.domain.usecase.MoviesInteractor
import com.akbararyo.ghibliapps.core.domain.usecase.MoviesUseCase
import com.akbararyo.ghibliapps.detail.DetailMoviesViewModel
import com.akbararyo.ghibliapps.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MoviesUseCase> { MoviesInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailMoviesViewModel(get()) }
}