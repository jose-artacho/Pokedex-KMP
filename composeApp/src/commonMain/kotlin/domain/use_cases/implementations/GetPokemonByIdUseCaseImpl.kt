package domain.use_cases.implementations

import data.util.asExternalModel
import domain.ResultData
import domain.model.PokemonDetail
import domain.repository.PokemonRepository
import domain.use_cases.GetPokemonByIdUseCase

class GetPokemonByIdUseCaseImpl(
    private val pokemonRepository: PokemonRepository
) : GetPokemonByIdUseCase {

    override suspend fun invoke(id: Int): ResultData<PokemonDetail> {
        val result = pokemonRepository.getPokemonById(id)

        return result.fold(
            onSuccess = {
                ResultData.success(it.asExternalModel())
            },
            onFailure = {
                ResultData.failure(it)
            })
    }
}