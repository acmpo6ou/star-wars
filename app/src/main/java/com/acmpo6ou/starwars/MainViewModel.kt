package com.acmpo6ou.starwars

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.acmpo6ou.starwars.model.Film
import com.acmpo6ou.starwars.model.Person

class MainViewModel : ViewModel() {
    val filmsList = mutableStateListOf<Film>()
    val peopleList = mutableStateListOf<Person>()

    fun initialize(filmsList: List<Film>) {
        this.filmsList.clear()
        this.filmsList.addAll(filmsList)
    }
}
