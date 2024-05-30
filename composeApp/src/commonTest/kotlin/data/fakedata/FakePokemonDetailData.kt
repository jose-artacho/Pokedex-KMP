package data.fakedata

import data.datasource.entities.FlavorTextEntriesResponse
import data.datasource.entities.LanguageResponse
import data.datasource.entities.PokemonDetailResponse

internal val fakeLanguageResponse = LanguageResponse(
    name = "en"
)

internal val fakeFlavorTextEntriesResponse = FlavorTextEntriesResponse(
    description = "description",
    language = fakeLanguageResponse
)

internal val fakePokemonDetailResponse = PokemonDetailResponse(
    id = 25,
    name = "Pikachu",
    flavorTextEntries = listOf(fakeFlavorTextEntriesResponse)
)