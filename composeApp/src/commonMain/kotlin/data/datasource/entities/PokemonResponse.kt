package data.datasource.entities

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResultResponse>
)

@Serializable
data class PokemonResultResponse(
    val name: String,
    val url: String
)