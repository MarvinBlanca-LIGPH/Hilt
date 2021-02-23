package com.example.hilttraining.data.repository

import com.example.hilttraining.data.Heroes
import retrofit2.Response

interface HeroRepository {
    suspend fun getHeroes(): Response<List<Heroes>>
}