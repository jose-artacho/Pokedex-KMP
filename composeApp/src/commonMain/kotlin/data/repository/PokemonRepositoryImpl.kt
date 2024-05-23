package data.repository

import data.datasource.cloud.PokemonDataSource
import data.util.asExternalModel
import domain.model.Pokemon
import domain.model.PokemonDetail
import domain.repository.PokemonRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PokemonRepositoryImpl: PokemonRepository, KoinComponent {
    private val pokemonDataSource: PokemonDataSource by inject()
    override suspend fun getPokemons(): List<Pokemon> {
        return pokemonDataSource.getPokemons().results.map { it.asExternalModel() }
    }

    override suspend fun getPokemonById(id: Int): PokemonDetail {
        return pokemonDataSource.getPokemonById(id).asExternalModel()
    }
}