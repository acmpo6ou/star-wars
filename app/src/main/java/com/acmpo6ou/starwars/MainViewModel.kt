package com.acmpo6ou.starwars

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acmpo6ou.starwars.model.Film
import com.acmpo6ou.starwars.model.MainRepo
import com.acmpo6ou.starwars.model.Person
import com.acmpo6ou.starwars.model.Starship
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private lateinit var repo: MainRepo
    val filmsList = mutableStateListOf<Film>()
    val peopleList = mutableStateListOf<Person>()
    val starshipList = mutableStateListOf<Starship>()

    fun initialize(repo: MainRepo) {
        this.repo = repo
        loadFilms()
    }

    private fun loadFilms() {
        viewModelScope.launch(Dispatchers.Default) {
            val films = repo.loadFilms()
            viewModelScope.launch(Dispatchers.Main) {
                filmsList.clear()
                filmsList.addAll(films)
            }
        }
    }

    fun loadFilms(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val films = repo.loadFilms(urls)
            viewModelScope.launch(Dispatchers.Main) {
                filmsList.clear()
                filmsList.addAll(films)
            }
        }
    }

    fun loadCharacters(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val characters = repo.loadCharacters(urls)
            viewModelScope.launch(Dispatchers.Main) {
                peopleList.clear()
                peopleList.addAll(characters)
            }
        }
    }

    fun loadStarships(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val starships = repo.loadStarships(urls)
            viewModelScope.launch(Dispatchers.Main) {
                starshipList.clear()
                starshipList.addAll(starships)
            }
        }
    }
}
