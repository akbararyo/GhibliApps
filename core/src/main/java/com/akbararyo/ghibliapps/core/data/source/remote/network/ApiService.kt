package com.akbararyo.ghibliapps.core.data.source.remote.network

import com.akbararyo.ghibliapps.core.data.source.remote.response.ListMoviesResponses
import retrofit2.http.GET

interface ApiService {
    @GET("films")
    suspend fun getList(): ListMoviesResponses
}