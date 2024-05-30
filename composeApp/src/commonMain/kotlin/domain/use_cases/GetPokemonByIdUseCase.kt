package domain.use_cases

import domain.ResultData
import domain.model.PokemonDetail

interface GetPokemonByIdUseCase {

    suspend operator fun invoke(id: Int): ResultData<PokemonDetail>
}