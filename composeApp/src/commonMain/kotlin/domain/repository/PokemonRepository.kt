package domain.repository

import domain.model.Pokemon
import domain.model.PokemonDetail

interface PokemonRepository {

    suspend fun getPokemons(limit: String): List<Pokemon>
    suspend fun getPokemonById(id: Int): PokemonDetail
}