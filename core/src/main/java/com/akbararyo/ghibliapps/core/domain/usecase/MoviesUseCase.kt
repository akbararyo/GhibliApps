package com.akbararyo.ghibliapps.core.domain.usecase

import com.akbararyo.ghibliapps.core.data.source.Resource
import com.akbararyo.ghibliapps.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesUseCase {
    fun getAllMovies(): Flow<Resource<List<Movies>>>

    fun getFavMovies(): Flow<List<Movies>>

    fun setFavMovies(movies: Movies, state: Boolean)
}