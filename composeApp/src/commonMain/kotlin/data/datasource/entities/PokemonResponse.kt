package data.datasource.entities

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(

    val id: Int,
    val name: String,
    val description: String,
    val weight: Int,
    val height: Int,
    val experience: Int,
    val url: String,
    val types: String,
)
