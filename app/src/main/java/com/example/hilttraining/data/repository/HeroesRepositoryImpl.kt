package com.example.hilttraining.data.repository

import com.example.hilttraining.data.*
import retrofit2.Response
import javax.inject.Inject

class HeroesRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : HeroRepository {
    override suspend fun getHeroes(): Response<List<Heroes>> {
        return apiInterface.getHeroes()
    }
}