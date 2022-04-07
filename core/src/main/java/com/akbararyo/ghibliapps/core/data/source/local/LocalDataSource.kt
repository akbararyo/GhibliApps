package com.akbararyo.ghibliapps.core.data.source.local

import com.akbararyo.ghibliapps.core.data.source.local.entity.MoviesEntity
import com.akbararyo.ghibliapps.core.data.source.local.room.MoviesDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val moviesDao: MoviesDao) {

    fun getAllMovies(): Flow<List<MoviesEntity>> = moviesDao.getAllMovies()

    fun getFavMovies(): Flow<List<MoviesEntity>> = moviesDao.getFavMovies()

    suspend fun insertTourism(moviesList: List<MoviesEntity>) =
        moviesDao.insertMovies(moviesList)

    fun setFavMovies(movies: MoviesEntity, newState: Boolean) {
        movies.isFavorite = newState
        moviesDao.updateFavMovies(movies)
    }
}