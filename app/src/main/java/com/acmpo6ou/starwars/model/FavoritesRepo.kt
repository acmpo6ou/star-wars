package com.acmpo6ou.starwars.model

import android.content.SharedPreferences

class FavoritesRepo(private val prefs: SharedPreferences) {
    fun getFavorites(key: String): List<String> {
        return prefs.getStringSet(key, setOf())?.toList() ?: listOf()
    }

    fun saveFavorites(key: String, ids: List<String>) {
        prefs.edit()
            .putStringSet(key, ids.toSet())
            .apply()
    }

    companion object {
        const val FAVORITE_FILMS = "favorite_films"
        const val FAVORITE_PEOPLE = "favorite_people"
        const val FAVORITE_STARSHIPS = "favorite_starships"
    }
}
