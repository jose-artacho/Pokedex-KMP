package domain.repository

import domain.model.Pokemon

interface PokemonRepository {

    suspend fun getPokemons(): List<Pokemon>
    suspend fun getPokemon(id: Int): Pokemon
}