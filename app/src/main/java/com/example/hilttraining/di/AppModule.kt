package com.example.hilttraining.di

import com.example.hilttraining.data.ApiInterface
import com.example.hilttraining.data.repository.*
import dagger.*
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class AppModule {
    @Binds
    abstract fun bindRepo(impl: HeroesRepositoryImpl): HeroRepository

    companion object {
        private const val baseUrl = "https://api.opendota.com/api/"

        @Singleton
        @Provides
        fun retrofit(): ApiInterface {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiInterface::class.java)
        }
    }
}