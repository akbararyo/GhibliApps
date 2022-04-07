package com.akbararyo.ghibliapps.detail

import androidx.lifecycle.ViewModel
import com.akbararyo.ghibliapps.core.domain.model.Movies
import com.akbararyo.ghibliapps.core.domain.usecase.MoviesUseCase

class DetailMoviesViewModel(private val moviesUseCase: MoviesUseCase) : ViewModel() {
    fun setFavMovies(movies: Movies, status: Boolean) =
        moviesUseCase.setFavMovies(movies, status)
}