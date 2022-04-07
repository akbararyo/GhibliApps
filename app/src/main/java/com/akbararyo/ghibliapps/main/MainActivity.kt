package com.akbararyo.ghibliapps.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.akbararyo.ghibliapps.R
import com.akbararyo.ghibliapps.core.data.source.Resource
import com.akbararyo.ghibliapps.core.ui.MoviesAdapter
import com.akbararyo.ghibliapps.databinding.ActivityMainBinding
import com.akbararyo.ghibliapps.detail.DetailMoviesActivity
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModel()
    private val moviesAdapter = MoviesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        moviesAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailMoviesActivity::class.java)
            intent.putExtra(DetailMoviesActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }
    }

    private fun getData() {
        mainViewModel.movies.observe(this, { movies ->
            if (movies != null) {
                when (movies) {
                    is Resource.Loading -> binding.pbMainMovies.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.pbMainMovies.visibility = View.GONE
                        moviesAdapter.setData(movies.data)
                    }
                    is Resource.Error -> {
                        binding.pbMainMovies.visibility = View.GONE
                        binding.vwMainError.root.visibility = View.VISIBLE
                        binding.vwMainError.tvErrorDesc.text =
                            movies.message ?: getString(R.string.text_error)
                    }
                }
            }
        })

        with(binding.rvMainMovies) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = moviesAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_fav -> {
                startActivity(
                    Intent(
                        this,
                        Class.forName("com.akbararyo.ghibliapps.favorite.FavoriteMoviesActivity")
                    )
                )
                return true
            }
        }
        return false
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvMainMovies.adapter = null
        _binding = null
    }
}