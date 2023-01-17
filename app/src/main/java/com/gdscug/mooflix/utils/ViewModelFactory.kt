package com.gdscug.mooflix.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.data.di.injection
import com.gdscug.mooflix.ui.home.HomeViewModel

class ViewModelFactory(private val movieRepository: MovieRepository):ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(HomeViewModel::class.java)->{
                HomeViewModel(movieRepository) as T
            }
            else -> throw Throwable("Unknown Viewmodel Class: $modelClass")
        }
    }

    companion object{
        @Volatile
        private  var instance : ViewModelFactory? = null
        fun getInstace(): ViewModelFactory = instance ?: synchronized(this){
            instance?: ViewModelFactory(injection.provideRepository())
        }
    }
}