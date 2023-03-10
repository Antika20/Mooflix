package com.gdscug.mooflix.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.MoviesEntity
import com.gdscug.mooflix.databinding.ActivityHomeBinding
import com.gdscug.mooflix.ui.detail.DetailActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAppBar()
        setupViewModel()
        setUpData()
    }

    // Mengubah judul Appbar
    private fun setUpAppBar() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    // inisialisasi iew Model
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this@HomeActivity,
            ViewModelProvider.NewInstanceFactory()
        )[HomeViewModel::class.java]
    }


    private fun setUpData() {
        val movieAdapter = MovieAdapter()
        val movies = viewModel.getMovies()
        movieAdapter.submitList(movies)

        binding.apply {
            rvMovies.layoutManager = GridLayoutManager(this@HomeActivity, 2)
            rvMovies.adapter = movieAdapter
        }

        // Jika diklik item nya
        movieAdapter.onItemCallback = object : MovieAdapter.OnItemCallback {
            override fun onItemCliked(movie: MoviesEntity) {
                Intent(this@HomeActivity, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_MOVIES, movie)
                    startActivity(this)
                }
            }

        }
    }
}