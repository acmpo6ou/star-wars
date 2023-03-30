package com.acmpo6ou.starwars.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Person(
    @SerialName("birth_year")
    val birthYear: String = "",
    @SerialName("eye_color")
    val eyeColor: String = "",
    val films: List<String> = listOf(),
    val gender: String = "",
    @SerialName("hair_color")
    val hairColor: String = "",
    val height: String = "",
    val homeworld: String = "",
    val mass: String = "",
    val name: String = "",
    @SerialName("skin_color")
    val skinColor: String = "",
    val created: String = "",
    val edited: String = "",
    val species: List<String> = listOf(),
    val starships: List<String> = listOf(),
    override val url: String = "",
    val vehicles: List<String> = listOf(),
) : Item

@Serializable
data class People(val result: List<Person>)
