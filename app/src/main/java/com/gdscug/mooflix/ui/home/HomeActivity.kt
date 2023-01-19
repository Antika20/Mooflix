package com.gdscug.mooflix.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.gdscug.mooflix.R
import com.gdscug.mooflix.data.local.MoviesEntity
import com.gdscug.mooflix.databinding.ActivityHomeBinding
import com.gdscug.mooflix.ui.detail.DetailActivity
import com.gdscug.mooflix.ui.login.LoginActivity
import com.gdscug.mooflix.utils.ViewModelFactory

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpAppBar()
        setupViewModel()
        setUpAction()
    }

    // Mengubah judul Appbar
    private fun setUpAppBar() {
        supportActionBar?.title = getString(R.string.home_title)
    }

    // inisialisasi iew Model
    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstace(this)
        viewModel = ViewModelProvider(
            this@HomeActivity,
            factory
        )[HomeViewModel::class.java]
    }

    //  menampung kondisi jika user udah login atau belum
    private fun setUpAction(){
        viewModel.getUser().observe(this) { user ->
            if(user.isLogin){
                setUpAdapter()
            } else{
                moveToLogin()
            }
        }
    }



    private fun setUpAdapter() {
        val movieAdapter = MovieAdapter()
        viewModel.getMovies().observe(this){
            val movies = it.results
            movieAdapter.submitList(movies)
        }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                logout()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

    private fun logout(){
        val builder = AlertDialog.Builder(this)
        val dialogLogOut = builder.setTitle(getString(R.string.confirm_logout))
            .setMessage(getString(R.string.logout_message))
            .setPositiveButton(getString(R.string.yes)){ dialog,_ ->
                viewModel.logout()
                finish()
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.no)){dialog,_ ->
                dialog.dismiss()
            }
        dialogLogOut.show()
    }

    private fun moveToLogin(){
        startActivity(Intent(this,LoginActivity::class.java))
        finish()
    }
}