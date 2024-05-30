package domain.use_cases.implementations

import data.util.asExternalModel
import domain.ResultData
import domain.model.Pokemon
import domain.repository.PokemonRepository
import domain.use_cases.GetPokemonsUseCase
import ui.PokedexViewModel.Companion.POKEDEX_KANTO

class GetPokemonsUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetPokemonsUseCase {

    override suspend fun invoke(limit: String?): ResultData<List<Pokemon>> {
        val result = pokemonRepository.getPokemons(limit ?: POKEDEX_KANTO)

        return result.fold(
            onSuccess = { it ->
                ResultData.success(it.results.map { pokemon -> pokemon.asExternalModel() })
            },
            onFailure = {
                ResultData.failure(it)
            })
    }
}