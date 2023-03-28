package com.acmpo6ou.starwars

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.acmpo6ou.starwars.model.Film

class MainViewModel : ViewModel() {
    val filmsList = mutableStateListOf<Film>()

    fun initialize(filmsList: List<Film>) {
        this.filmsList.clear()
        this.filmsList.addAll(filmsList)
    }
}
