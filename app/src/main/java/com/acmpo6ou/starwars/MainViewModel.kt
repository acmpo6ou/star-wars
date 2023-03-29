package com.acmpo6ou.starwars

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acmpo6ou.starwars.model.*
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_FILMS
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_PEOPLE
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_STARSHIPS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private lateinit var mainRepo: MainRepo
    private lateinit var favoritesRepo: FavoritesRepo

    val filmsList = mutableStateListOf<Film>()
    val peopleList = mutableStateListOf<Person>()
    val starshipList = mutableStateListOf<Starship>()

    val favoriteFilms = mutableStateListOf<String>()
    val favoritePeople = mutableStateListOf<String>()
    val favoriteStarships = mutableStateListOf<String>()

    fun initialize(mainRepo: MainRepo) {
        this.mainRepo = mainRepo
        loadFilms()
        loadFavorites()
    }

    private fun loadFavorites() {
        val favorites = mapOf(
            FAVORITE_FILMS to favoriteFilms,
            FAVORITE_PEOPLE to favoritePeople,
            FAVORITE_STARSHIPS to favoriteStarships,
        )
        for ((key, list) in favorites) {
            list.clear()
            list.addAll(favoritesRepo.getFavorites(key))
        }
    }

    private fun loadFilms() {
        viewModelScope.launch(Dispatchers.Default) {
            val films = mainRepo.loadFilms()
            viewModelScope.launch(Dispatchers.Main) {
                filmsList.clear()
                filmsList.addAll(films)
            }
        }
    }

    fun loadFilms(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val films = mainRepo.loadFilms(urls)
            viewModelScope.launch(Dispatchers.Main) {
                filmsList.clear()
                filmsList.addAll(films)
            }
        }
    }

    fun loadCharacters(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val characters = mainRepo.loadCharacters(urls)
            viewModelScope.launch(Dispatchers.Main) {
                peopleList.clear()
                peopleList.addAll(characters)
            }
        }
    }

    fun loadStarships(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val starships = mainRepo.loadStarships(urls)
            viewModelScope.launch(Dispatchers.Main) {
                starshipList.clear()
                starshipList.addAll(starships)
            }
        }
    }
}
