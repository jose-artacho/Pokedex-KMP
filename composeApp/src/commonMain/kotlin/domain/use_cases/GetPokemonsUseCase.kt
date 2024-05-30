package domain.use_cases

import domain.ResultData
import domain.model.Pokemon

interface GetPokemonsUseCase {

    suspend operator fun invoke(limit: String? = null): ResultData<List<Pokemon>>
}