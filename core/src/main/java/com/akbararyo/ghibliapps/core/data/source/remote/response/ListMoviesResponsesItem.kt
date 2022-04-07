package com.akbararyo.ghibliapps.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListMoviesResponsesItem(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("movie_banner")
    val movieBanner: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("rt_score")
    val rtScore: String,
    @SerializedName("running_time")
    val runningTime: String,
    @SerializedName("director")
    val director: String,
    @SerializedName("producer")
    val producer: String,
    @SerializedName("title")
    val title: String
)