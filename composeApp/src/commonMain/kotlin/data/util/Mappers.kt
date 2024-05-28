package data.util

import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResultResponse
import domain.model.Pokemon
import domain.model.PokemonDetail

fun PokemonResultResponse.asExternalModel() = Pokemon(
    name = name,
    url = url
)

fun PokemonDetailResponse.asExternalModel() = PokemonDetail(
    id = id,
    name = name,
    description = flavorTextEntries.first { it.language.name == "en" }.description.replace(
        "\n",
        " "
    )
)