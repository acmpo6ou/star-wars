package com.acmpo6ou.starwars.client

import com.acmpo6ou.starwars.ApiEndpoints
import com.acmpo6ou.starwars.model.Starship
import com.acmpo6ou.starwars.model.Starships
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface StarshipsClient {
    @GET(ApiEndpoints.STARSHIPS)
    fun getStarships(): Call<Starships>

    @GET
    fun getStarship(@Url url: String): Call<Starship>
}
