package com.acmpo6ou.starwars.client

import com.acmpo6ou.starwars.model.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PeopleClient {
    @GET
    fun getPerson(@Url url: String): Call<Person>
}
