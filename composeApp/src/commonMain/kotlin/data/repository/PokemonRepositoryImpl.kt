package data.repository

import data.datasource.cloud.PokemonDataSource
import data.util.asExternalModel
import domain.model.Pokemon
import domain.repository.PokemonRepository

class PokemonRepositoryImpl (
    private val pokemonDataSource: PokemonDataSource

): PokemonRepository {
    override suspend fun getPokemons(): List<Pokemon> {
        return pokemonDataSource.getPokemons().map { it.asExternalModel() }
    }

    override suspend fun getPokemon(id: Int): Pokemon {
        return pokemonDataSource.getPokemon(id).asExternalModel()
    }
}