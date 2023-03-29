package com.acmpo6ou.starwars.client

import com.acmpo6ou.starwars.model.Starship
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface StarshipsClient {
    @GET
    fun getStarship(@Url url: String): Call<Starship>
}
