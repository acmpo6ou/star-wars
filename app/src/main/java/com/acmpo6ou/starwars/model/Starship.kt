package com.acmpo6ou.starwars.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Starship(
    val MGLT: String = "",
    @SerialName("cargo_capacity")
    val cargoCapacity: String = "",
    val consumables: String = "",
    @SerialName("cost_in_credits")
    val costInCredits: String = "",
    val crew: String = "",
    @SerialName("hyperdrive_rating")
    val hyperdriveRating: String = "",
    val length: String = "",
    val manufacturer: String = "",
    @SerialName("max_atmosphering_speed")
    val maxAtmospheringSpeed: String = "",
    val model: String = "",
    val name: String = "",
    val passengers: String = "",
    val films: List<String> = listOf(),
    val pilots: List<String> = listOf(),
    @SerialName("starship_class")
    val starshipClass: String = "",
    override val url: String = "",
) : Item

@Serializable
data class Starships(val results: List<Starship>)
