package data.datasource.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailResponse(

    val id: Int,
    val name: String,
    @SerialName("flavor_text_entries")
    val flavorTextEntries: List<FlavorTextEntriesResponse>
)

@Serializable
data class FlavorTextEntriesResponse(
    @SerialName("flavor_text")
    val description: String,
    val language: LanguageResponse
)

@Serializable
data class LanguageResponse(
    val name: String
)


