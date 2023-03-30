package com.acmpo6ou.starwars

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acmpo6ou.starwars.model.*
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_FILMS
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_PEOPLE
import com.acmpo6ou.starwars.model.FavoritesRepo.Companion.FAVORITE_STARSHIPS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction0
import kotlin.reflect.KSuspendFunction1

class MainViewModel : ViewModel() {
    private lateinit var mainRepo: MainRepo
    private lateinit var favoritesRepo: FavoritesRepo

    val searchText = MutableLiveData("")
    val loading = MutableLiveData(true)

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
        loadFilms()
    }

    private fun <T> getItems(
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
        getItems(favoriteFilms, favoriteFilmUrls, mainRepo::getFilms)
        getItems(favoritePeople, favoritePeopleUrls, mainRepo::getPeople)
        getItems(favoriteStarships, favoriteStarshipUrls, mainRepo::getStarships)
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

    private fun <T> loadItems(
        list: SnapshotStateList<T>,
        loader: KSuspendFunction0<List<T>>,
    ) {
        loading.value = true
        viewModelScope.launch(Dispatchers.Default) {
            val items = loader()
            viewModelScope.launch(Dispatchers.Main) {
                list.clear()
                list.addAll(items)
                loading.value = false
            }
        }
    }

    fun loadFilms() = loadItems(filmsList, mainRepo::loadFilms)
    fun loadPeople() = loadItems(peopleList, mainRepo::loadPeople)
    fun loadStarships() = loadItems(starshipList, mainRepo::loadStarships)

    fun loadFilms(urls: List<String>) = getItems(filmsList, urls, mainRepo::getFilms)
    fun loadPeople(urls: List<String>) = getItems(peopleList, urls, mainRepo::getPeople)
    fun loadStarships(urls: List<String>) = getItems(starshipList, urls, mainRepo::getStarships)
}
