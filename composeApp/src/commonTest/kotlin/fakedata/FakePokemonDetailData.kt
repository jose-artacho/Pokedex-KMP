package fakedata

import data.datasource.entities.FlavorTextEntriesResponse
import data.datasource.entities.LanguageResponse
import data.datasource.entities.PokemonDetailResponse
import domain.model.PokemonDetail

internal val fakeLanguageResponse = LanguageResponse(
    name = "en"
)

internal val fakeFlavorTextEntriesResponse = FlavorTextEntriesResponse(
    description = "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.",
    language = fakeLanguageResponse
)

internal val fakePokemonDetailResponse = PokemonDetailResponse(
    id = 6,
    name = "charizard",
    flavorTextEntries = listOf(fakeFlavorTextEntriesResponse)
)

const val pokemonDetailJsonResponse = """
    {
   "flavor_text_entries":[
      {
         "flavor_text":"Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally.",
         "language":{
            "name":"en",
            "url":"https://pokeapi.co/api/v2/language/9/"
         },
         "version":{
            "name":"red",
            "url":"https://pokeapi.co/api/v2/version/1/"
         }
      }
   ],
   "id":6,
   "name":"charizard"
}
"""

internal val fakePokemonDetail = PokemonDetail(
    id = 6,
    name = "charizard",
    description = "Spits fire that is hot enough to melt boulders. Known to cause forest fires unintentionally."
)