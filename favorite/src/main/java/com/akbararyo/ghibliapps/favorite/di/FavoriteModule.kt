package com.akbararyo.ghibliapps.favorite.di

import com.akbararyo.ghibliapps.favorite.FavoriteMoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMoviesViewModel(get()) }
}