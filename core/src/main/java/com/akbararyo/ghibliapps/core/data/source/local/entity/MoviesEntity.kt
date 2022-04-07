package com.akbararyo.ghibliapps.core.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "movies")
data class MoviesEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "image")
    var image: String,

    @ColumnInfo(name = "movieBanner")
    var movieBanner: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "release")
    var release: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "score")
    var score: String,

    @ColumnInfo(name = "director")
    var director: String,

    @ColumnInfo(name = "producer")
    var producer: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable