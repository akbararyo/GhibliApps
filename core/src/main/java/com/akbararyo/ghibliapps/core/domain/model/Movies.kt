package com.akbararyo.ghibliapps.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val movieId: String,
    val title: String,
    val image: String,
    val movieBanner: String,
    val description: String,
    val release: String,
    val duration: String,
    val score: String,
    val director: String,
    val producer: String,
    val isFavorite: Boolean
) : Parcelable