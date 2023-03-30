package com.acmpo6ou.starwars

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acmpo6ou.starwars.model.*
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_FILMS
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_PEOPLE
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_STARSHIPS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction1

class MainViewModel : ViewModel() {
    private lateinit var mainRepo: MainRepo
    private lateinit var favoritesRepo: FavoritesRepo

    val filmsList = mutableStateListOf<Film>()
    val peopleList = mutableStateListOf<Person>()
    val starshipList = mutableStateListOf<Starship>()

    val favoriteFilmUrls = mutableStateListOf<String>()
    val favoritePeopleUrls = mutableStateListOf<String>()
    val favoriteStarshipUrls = mutableStateListOf<String>()

    val favoriteFilms = mutableStateListOf<Film>()
    val favoritePeople = mutableStateListOf<Person>()
    val favoriteStarships = mutableStateListOf<Starship>()

    private val favoriteUrls = mapOf(
        FAVORITE_FILMS to favoriteFilmUrls,
        FAVORITE_PEOPLE to favoritePeopleUrls,
        FAVORITE_STARSHIPS to favoriteStarshipUrls,
    )

    private val favorites = mapOf(
        FAVORITE_FILMS to favoriteFilms,
        FAVORITE_PEOPLE to favoritePeople,
        FAVORITE_STARSHIPS to favoriteStarships,
    )

    fun initialize(mainRepo: MainRepo, favoritesRepo: FavoritesRepo) {
        this.mainRepo = mainRepo
        this.favoritesRepo = favoritesRepo
        loadFavoriteUrls()
    }

    private fun <T> getFavorites(
        list: SnapshotStateList<T>,
        urls: List<String>,
        getter: KSuspendFunction1<List<String>, List<T>>,
    ) {
        viewModelScope.launch(Dispatchers.Default) {
            val items = getter(urls)
            viewModelScope.launch(Dispatchers.Main) {
                list.clear()
                list.addAll(items)
            }
        }
    }

    private fun loadFavoriteUrls() {
        for ((key, list) in favoriteUrls) {
            list.clear()
            list.addAll(favoritesRepo.getFavoriteUrls(key))
        }
    }

    fun loadFavorites() {
        getFavorites(favoriteFilms, favoriteFilmUrls, mainRepo::getFilms)
        getFavorites(favoritePeople, favoritePeopleUrls, mainRepo::getPeople)
        getFavorites(favoriteStarships, favoriteStarshipUrls, mainRepo::getStarships)
    }

    fun addFavorite(key: String, value: String) {
        favoriteUrls[key]?.add(value)
        favoritesRepo.saveFavoriteUrls(key, favoriteUrls[key] ?: listOf())
    }

    fun removeFavorite(key: String, value: String) {
        favoriteUrls[key]?.remove(value)
        favoritesRepo.saveFavoriteUrls(key, favoriteUrls[key] ?: listOf())
        favorites[key]?.firstOrNull { it.url == value }?.let {
            favorites[key]?.remove(it)
        }
    }

    fun loadFilms() {
        viewModelScope.launch(Dispatchers.Default) {
            val films = mainRepo.loadFilms()
            viewModelScope.launch(Dispatchers.Main) {
                filmsList.clear()
                filmsList.addAll(films)
            }
        }
    }

    fun loadPeople() {
        viewModelScope.launch(Dispatchers.Default) {
            val people = mainRepo.loadPeople()
            viewModelScope.launch(Dispatchers.Main) {
                peopleList.clear()
                peopleList.addAll(people)
            }
        }
    }

    fun loadStarships() {
        viewModelScope.launch(Dispatchers.Default) {
            val starships = mainRepo.loadStarships()
            viewModelScope.launch(Dispatchers.Main) {
                starshipList.clear()
                starshipList.addAll(starships)
            }
        }
    }

    fun loadFilms(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val films = mainRepo.getFilms(urls)
            viewModelScope.launch(Dispatchers.Main) {
                filmsList.clear()
                filmsList.addAll(films)
            }
        }
    }

    fun loadCharacters(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val characters = mainRepo.getPeople(urls)
            viewModelScope.launch(Dispatchers.Main) {
                peopleList.clear()
                peopleList.addAll(characters)
            }
        }
    }

    fun loadStarships(urls: List<String>) {
        viewModelScope.launch(Dispatchers.Default) {
            val starships = mainRepo.getStarships(urls)
            viewModelScope.launch(Dispatchers.Main) {
                starshipList.clear()
                starshipList.addAll(starships)
            }
        }
    }
}
