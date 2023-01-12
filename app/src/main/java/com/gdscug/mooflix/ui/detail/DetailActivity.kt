package com.gdscug.mooflix.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.MoviesEntity
import com.gdscug.mooflix.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private var data : MoviesEntity? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        val movies = intent.extras
        if(movies != null){
            data = intent.getParcelableExtra(EXTRA_MOVIES)
            data?.let {
                setUpData(it)
            }
        }
    }

    private fun setUpData(moviesEntity: MoviesEntity){
        binding.apply {
            tvDetailTitle.text = moviesEntity.title
            tvReleaseDate.text = moviesEntity.releaaseDate
            tvVoteAverage.text = moviesEntity.voteAverage
            tvOverview.text = moviesEntity.overview
            Glide.with(this@DetailActivity).load(moviesEntity.posterPath).into(ivPosterDetail)
        }
    }

    // Mengubah judul Appbar
    private fun setupView(){
        supportActionBar?.apply {
            title = getString(R.string.detail_title)
            // membuat tanda back
            setDisplayHomeAsUpEnabled(true)
        }
    }

    // handle tanda back jika di klik
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    companion object{
        const val  EXTRA_MOVIES = "extra_movie"
    }
}