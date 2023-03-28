package com.acmpo6ou.starwars.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Film(
    @SerialName("episode_id")
    val episodeId: Int = 0,
    @SerialName("release_date")
    val releaseDate: String = "",
    val title: String = "",
    @SerialName("opening_crawl")
    val openingCrawl: String = "",
    val characters: List<String> = listOf(),
    val starships: List<String> = listOf(),
    val director: String = "",
    val producer: String = "",
    val created: String = "",
    val edited: String = "",
    val url: String = "",
)

@Serializable
data class Films(val results: List<Film>)
