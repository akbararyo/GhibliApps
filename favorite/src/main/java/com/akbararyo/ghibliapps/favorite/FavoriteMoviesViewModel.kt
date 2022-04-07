package com.akbararyo.ghibliapps.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akbararyo.ghibliapps.core.domain.usecase.MoviesUseCase

class FavoriteMoviesViewModel(moviesUseCase: MoviesUseCase) : ViewModel() {
    val movies = moviesUseCase.getFavMovies().asLiveData()
}