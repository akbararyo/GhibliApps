package com.akbararyo.ghibliapps.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.akbararyo.ghibliapps.core.domain.usecase.MoviesUseCase

class MainViewModel (moviesUseCase: MoviesUseCase) : ViewModel(){
    val movies = moviesUseCase.getAllMovies().asLiveData()
}