package com.akbararyo.ghibliapps.core.domain.usecase

import com.akbararyo.ghibliapps.core.domain.model.Movies
import com.akbararyo.ghibliapps.core.domain.repository.MoviesRepositoryImpl

class MoviesInteractor(private val moviesRepositoryImpl: MoviesRepositoryImpl): MoviesUseCase {
    override fun getAllMovies() = moviesRepositoryImpl.getAllMovies()

    override fun getFavMovies() = moviesRepositoryImpl.getFavMovies()

    override fun setFavMovies(movies: Movies, state: Boolean) = moviesRepositoryImpl.setFavMovies(movies, state)
}