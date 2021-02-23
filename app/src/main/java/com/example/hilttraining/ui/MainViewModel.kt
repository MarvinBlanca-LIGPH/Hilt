package com.example.hilttraining.ui

import androidx.lifecycle.*
import com.example.hilttraining.data.Heroes
import com.example.hilttraining.data.repository.HeroRepository
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: HeroRepository
) : ViewModel() {
    fun getHeroes(): LiveData<List<Heroes>> {
        val heroes = MutableLiveData<List<Heroes>>()
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getHeroes()
            viewModelScope.launch {
                if (response.isSuccessful) {
                    heroes.value = response.body()
                }
            }
        }
        return heroes
    }
}