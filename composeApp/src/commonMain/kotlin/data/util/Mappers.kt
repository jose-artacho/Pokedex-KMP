package data.util

import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResultResponse
import domain.model.Pokemon
import domain.model.PokemonDetail

fun PokemonResultResponse.asExternalModel() = Pokemon(
    name = name,
    image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/{id}.png",
    url = url
)
fun PokemonDetailResponse.asExternalModel() = PokemonDetail(
    id = id,
    name = name,
    description = description,
    weight = weight,
    height = height,
    experience = experience,
    url = url,
    types = types
)