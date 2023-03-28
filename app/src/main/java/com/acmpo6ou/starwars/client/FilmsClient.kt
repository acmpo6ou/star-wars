package com.acmpo6ou.starwars.client

import com.acmpo6ou.starwars.ApiEndpoints
import com.acmpo6ou.starwars.model.Films
import retrofit2.Call
import retrofit2.http.GET

interface FilmsClient {
    @GET(ApiEndpoints.FILMS)
    fun getFilms(): Call<Films>
}
