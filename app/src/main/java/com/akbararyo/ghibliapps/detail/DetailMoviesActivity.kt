package com.akbararyo.ghibliapps.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.akbararyo.ghibliapps.R
import com.akbararyo.ghibliapps.core.domain.model.Movies
import com.akbararyo.ghibliapps.databinding.ActivityDetailMoviesBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailMoviesActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private lateinit var binding: ActivityDetailMoviesBinding
    private val detailMoviesViewModel: DetailMoviesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)

        binding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailMovies = intent.getParcelableExtra<Movies>(EXTRA_DATA)
        showDetailMovies(detailMovies)
    }

    @SuppressLint("SetTextI18n")
    private fun showDetailMovies(detailMovies: Movies?) {
        detailMovies?.let {
            supportActionBar?.title = detailMovies.title
            with(binding) {
                Glide.with(this@DetailMoviesActivity)
                    .load(detailMovies.movieBanner)
                    .into(binding.ivDetailImage)

                Glide.with(this@DetailMoviesActivity)
                    .load(detailMovies.image)
                    .into(binding.imgDetailPoster)

                tvDetailTitle.text = detailMovies.title
                tvDetailRelease.text = "Release Year : ${detailMovies.release}"
                tvDetailDuration.text = "Duration : ${detailMovies.duration} Minute"
                tvDetailScore.text = "RT Score : ${detailMovies.score}"
                tvDetailProducer.text = detailMovies.producer
                tvDetailDirector.text = detailMovies.director
                tvDetailDesc.text = detailMovies.description

                var statusFavorite = detailMovies.isFavorite
                setStatusFav(statusFavorite)

                fabDetailFav.setOnClickListener {
                    statusFavorite = !statusFavorite
                    detailMoviesViewModel.setFavMovies(detailMovies, statusFavorite)
                    setStatusFav(statusFavorite)
                }
            }
        }
    }

    private fun setStatusFav(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabDetailFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_active
                )
            )
        } else {
            binding.fabDetailFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_non
                )
            )
        }
    }

}