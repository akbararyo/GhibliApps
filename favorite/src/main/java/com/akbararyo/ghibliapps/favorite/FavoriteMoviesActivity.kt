package com.akbararyo.ghibliapps.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbararyo.ghibliapps.core.ui.MoviesAdapter
import com.akbararyo.ghibliapps.detail.DetailMoviesActivity
import com.akbararyo.ghibliapps.favorite.databinding.ActivityFavoriteMoviesBinding
import com.akbararyo.ghibliapps.favorite.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteMoviesActivity : AppCompatActivity() {
    private var _binding: ActivityFavoriteMoviesBinding? = null
    private val binding get() = _binding

    private val favoriteViewModel: FavoriteMoviesViewModel by viewModel()
    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteMoviesBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        loadKoinModules(favoriteModule)
        supportActionBar?.title = getString(R.string.favorite)

        getData()

        moviesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
    }

    private fun getData() {
        binding?.pbFavMovies?.visibility = View.VISIBLE
        favoriteViewModel.movies.observe(this, { movies ->
            binding?.pbFavMovies?.visibility = View.GONE
            moviesAdapter.setData(movies)
            if (movies.isNullOrEmpty()) {
                binding?.tvFavEmpty?.visibility = View.VISIBLE
            } else {
                with(binding?.rvFavMovies) {
                    this?.layoutManager = LinearLayoutManager(this?.context)
                    this?.setHasFixedSize(true)
                    this?.adapter = moviesAdapter
                }
            }
        })
    }
}