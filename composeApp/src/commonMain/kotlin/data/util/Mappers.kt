package data.util

import data.datasource.entities.PokemonResponse
import domain.model.Pokemon

fun PokemonResponse.asExternalModel() = Pokemon(
    id = id,
    name = name,
    description = description,
    weight = weight,
    height = height,
    experience = experience,
    url = url,
    types = types
)