package data.datasource

import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResponse

interface PokemonDataSource {
    suspend fun getPokemons(limit: String): Result<PokemonResponse>

    suspend fun getPokemonById(id: Int): Result<PokemonDetailResponse>
}