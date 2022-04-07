package com.akbararyo.ghibliapps.core.data.source

import com.akbararyo.ghibliapps.core.data.source.local.LocalDataSource
import com.akbararyo.ghibliapps.core.data.source.remote.RemoteDataSource
import com.akbararyo.ghibliapps.core.data.source.remote.network.ApiResponse
import com.akbararyo.ghibliapps.core.data.source.remote.response.ListMoviesResponsesItem
import com.akbararyo.ghibliapps.core.domain.model.Movies
import com.akbararyo.ghibliapps.core.domain.repository.MoviesRepositoryImpl
import com.akbararyo.ghibliapps.core.utils.AppExecutors
import com.akbararyo.ghibliapps.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): MoviesRepositoryImpl {
    override fun getAllMovies(): Flow<Resource<List<Movies>>> =
        object : NetworkBoundResource<List<Movies>, List<ListMoviesResponsesItem>>() {
        override fun loadFromDB(): Flow<List<Movies>> {
            return localDataSource.getAllMovies().map { DataMapper.moviesEntitiesToDomain(it) }
        }

        override fun shouldFetch(data: List<Movies>?): Boolean =
            data == null || data.isEmpty()

        override suspend fun createCall(): Flow<ApiResponse<List<ListMoviesResponsesItem>>> =
            remoteDataSource.getAllMovie()

        override suspend fun saveCallResult(data: List<ListMoviesResponsesItem>) {
            val moviesList = DataMapper.moviesResponsesToEntities(data)
            localDataSource.insertTourism(moviesList)
        }
    }.asFlow()

    override fun getFavMovies(): Flow<List<Movies>> {
        return localDataSource.getFavMovies().map { DataMapper.moviesEntitiesToDomain(it) }
    }

    override fun setFavMovies(movies: Movies, state: Boolean) {
        val moviesEntity = DataMapper.moviesDomainToEntity(movies)
        appExecutors.diskIO().execute { localDataSource.setFavMovies(moviesEntity, state) }
    }

}