package com.acmpo6ou.starwars.model

import android.content.SharedPreferences

class FavoritesRepo(private val prefs: SharedPreferences) {

    /**
     * Using shared preferences, loads urls of items added to favorites.
     *
     * @param key the key for the items (e.g. FAVORITE_FILMS to load favorite films).
     */
    fun getFavoriteUrls(key: String): List<String> {
        return prefs.getStringSet(key, setOf())?.toList() ?: listOf()
    }

    /**
     * Saves [urls] to shared preferences.
     *
     * @param key the key for the items (e.g. FAVORITE_FILMS to save favorite films).
     */
    fun saveFavoriteUrls(key: String, urls: List<String>) {
        prefs.edit()
            .putStringSet(key, urls.toSet())
            .apply()
    }

    companion object {
        const val FAVORITES = "favorites"
        const val FAVORITE_FILMS = "favorite_films"
        const val FAVORITE_PEOPLE = "favorite_people"
        const val FAVORITE_STARSHIPS = "favorite_starships"
    }
}
