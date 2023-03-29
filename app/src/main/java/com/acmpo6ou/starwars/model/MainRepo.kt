package com.acmpo6ou.starwars.model

import com.acmpo6ou.starwars.client.FilmsClient
import com.acmpo6ou.starwars.client.PeopleClient
import com.acmpo6ou.starwars.client.StarshipsClient
import retrofit2.Retrofit
import retrofit2.awaitResponse

class MainRepo(private val retrofit: Retrofit) {

    suspend fun loadFilms(): List<Film> {
        val service = retrofit.create(FilmsClient::class.java)
        return service.getFilms()
            .awaitResponse()
            .body()
            ?.results
            ?: listOf()
    }

    suspend fun loadFilms(urls: List<String>): List<Film> {
        val service = retrofit.create(FilmsClient::class.java)
        val films = mutableListOf<Film>()
        for (url in urls) {
            service.getFilm(url)
                .awaitResponse()
                .body()
                ?.let {
                    films.add(it)
                }
        }
        return films
    }

    suspend fun loadCharacters(urls: List<String>): List<Person> {
        val service = retrofit.create(PeopleClient::class.java)
        val people = mutableListOf<Person>()
        for (url in urls) {
            service.getPerson(url)
                .awaitResponse()
                .body()
                ?.let {
                    people.add(it)
                }
        }
        return people
    }

    suspend fun loadStarships(urls: List<String>): List<Starship> {
        val service = retrofit.create(StarshipsClient::class.java)
        val starships = mutableListOf<Starship>()
        for (url in urls) {
            service.getStarship(url)
                .awaitResponse()
                .body()
                ?.let {
                    starships.add(it)
                }
        }
        return starships
    }
}
