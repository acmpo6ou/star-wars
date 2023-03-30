package com.acmpo6ou.starwars.model

import android.content.SharedPreferences
import com.acmpo6ou.starwars.client.FilmsClient
import retrofit2.Retrofit

class FavoritesRepo(
    private val retrofit: Retrofit,
    private val prefs: SharedPreferences,
) {
    fun getFavoriteUrls(key: String): List<String> {
        return prefs.getStringSet(key, setOf())?.toList() ?: listOf()
    }

    fun saveFavoriteUrls(key: String, ids: List<String>) {
        prefs.edit()
            .putStringSet(key, ids.toSet())
            .apply()
    }

    companion object {
        const val FAVORITES = "favorites"
        const val FAVORITE_FILMS = "favorite_films"
        const val FAVORITE_PEOPLE = "favorite_people"
        const val FAVORITE_STARSHIPS = "favorite_starships"
    }
}
