package com.akbararyo.ghibliapps.core.domain.repository

import com.akbararyo.ghibliapps.core.data.source.Resource
import com.akbararyo.ghibliapps.core.domain.model.Movies
import kotlinx.coroutines.flow.Flow

interface MoviesRepositoryImpl {
    fun getAllMovies(): Flow<Resource<List<Movies>>>

    fun getFavMovies(): Flow<List<Movies>>

    fun setFavMovies(movies: Movies, state: Boolean)
}