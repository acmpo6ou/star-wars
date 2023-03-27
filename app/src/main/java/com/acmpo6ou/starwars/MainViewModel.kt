package com.acmpo6ou.starwars

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.acmpo6ou.starwars.model.Film

class MainViewModel : ViewModel() {
    lateinit var filmsList: SnapshotStateList<Film>

    fun initialize(filmsList: List<Film>) {
        this.filmsList = mutableStateListOf()
        this.filmsList.addAll(filmsList)
    }
}
