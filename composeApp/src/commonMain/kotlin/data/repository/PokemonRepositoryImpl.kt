package data.repository

import data.datasource.PokemonDataSource
import data.datasource.cloud.PokemonDataSourceImpl
import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResponse
import data.util.asExternalModel
import domain.model.Pokemon
import domain.model.PokemonDetail
import domain.repository.PokemonRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

internal class PokemonRepositoryImpl(
    private val pokemonDataSource: PokemonDataSource
): PokemonRepository {
    override suspend fun getPokemons(limit: String): Result<PokemonResponse> =
        pokemonDataSource.getPokemons(limit)

    override suspend fun getPokemonById(id: Int): Result<PokemonDetailResponse> =
        pokemonDataSource.getPokemonById(id)
}