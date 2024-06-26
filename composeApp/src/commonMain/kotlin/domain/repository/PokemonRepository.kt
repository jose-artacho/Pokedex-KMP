package domain.repository

import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResponse
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemons(limit: String): Result<PokemonResponse>
    suspend fun getPokemonById(id: Int): Result<PokemonDetailResponse>
}