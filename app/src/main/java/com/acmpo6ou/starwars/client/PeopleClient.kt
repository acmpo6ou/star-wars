package com.acmpo6ou.starwars.client

import com.acmpo6ou.starwars.ApiEndpoints
import com.acmpo6ou.starwars.model.People
import com.acmpo6ou.starwars.model.Person
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PeopleClient {
    @GET(ApiEndpoints.PEOPLE)
    fun getPeople(): Call<People>

    @GET
    fun getPerson(@Url url: String): Call<Person>
}
