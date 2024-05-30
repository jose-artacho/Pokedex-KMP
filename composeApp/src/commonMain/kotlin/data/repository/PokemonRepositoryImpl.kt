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

class PokemonRepositoryImpl: PokemonRepository, KoinComponent {
    private val pokemonDataSource: PokemonDataSource by inject()
    override suspend fun getPokemons(limit: String): Result<PokemonResponse> =
        pokemonDataSource.getPokemons(limit)

    override suspend fun getPokemonById(id: Int): Result<PokemonDetailResponse> =
        pokemonDataSource.getPokemonById(id)
}