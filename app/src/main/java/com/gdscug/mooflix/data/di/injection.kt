package com.gdscug.mooflix.data.di

import com.gdscug.mooflix.data.MovieRepository
import com.gdscug.mooflix.data.remote.response.RemoteDataSource

object injection {
   fun provideRepository():MovieRepository{
       val remoteDataSource = RemoteDataSource.getInstance()
       return MovieRepository.getInstance(remoteDataSource)
   }

}