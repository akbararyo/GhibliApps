package com.akbararyo.ghibliapps.core.data.source.local.room

import androidx.room.*
import com.akbararyo.ghibliapps.core.data.source.local.entity.MoviesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MoviesEntity>>

    @Query("SELECT * FROM movies where isFavorite = 1")
    fun getFavMovies(): Flow<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateFavMovies(movies: MoviesEntity)
}