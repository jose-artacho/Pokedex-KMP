package domain.use_cases

import domain.repository.PokemonRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetPokemonsUseCase: KoinComponent {
    private val repository: PokemonRepository by inject()

    suspend fun invoke() = repository.getPokemons()
}