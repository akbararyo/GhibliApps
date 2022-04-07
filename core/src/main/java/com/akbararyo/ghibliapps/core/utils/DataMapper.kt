package com.akbararyo.ghibliapps.core.utils

import com.akbararyo.ghibliapps.core.data.source.local.entity.MoviesEntity
import com.akbararyo.ghibliapps.core.data.source.remote.response.ListMoviesResponsesItem
import com.akbararyo.ghibliapps.core.domain.model.Movies

object DataMapper {
    fun moviesResponsesToEntities(input: List<ListMoviesResponsesItem>): List<MoviesEntity> {
        val moviesList = ArrayList<MoviesEntity>()
        input.map {
            val movies = MoviesEntity(
                movieId = it.id,
                title = it.title,
                image = it.image,
                movieBanner = it.movieBanner,
                description = it.description,
                release = it.releaseDate,
                duration = it.runningTime,
                score = it.rtScore,
                producer = it.producer,
                director = it.director,
                isFavorite = false
            )
            moviesList.add(movies)
        }
        return moviesList
    }

    fun moviesEntitiesToDomain(input: List<MoviesEntity>): List<Movies> =
        input.map {
            Movies(
                movieId = it.movieId,
                title = it.title,
                image = it.image,
                movieBanner = it.movieBanner,
                description = it.description,
                release = it.release,
                duration = it.duration,
                score = it.score,
                director = it.director,
                producer = it.producer,
                isFavorite = it.isFavorite
            )
        }

    fun moviesDomainToEntity(input: Movies) = MoviesEntity(
        movieId = input.movieId,
        title = input.title,
        image = input.image,
        movieBanner = input.movieBanner,
        description = input.description,
        release = input.release,
        duration = input.duration,
        score = input.score,
        director = input.director,
        producer = input.producer,
        isFavorite = input.isFavorite
    )
}