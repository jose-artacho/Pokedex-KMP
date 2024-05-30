package domain.use_cases

import domain.repository.PokemonRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import ui.PokedexViewModel.Companion.POKEDEX_KANTO

class GetPokemonsUseCase: KoinComponent {
    private val repository: PokemonRepository by inject()

    suspend fun invoke(limit: String? = null) = repository.getPokemons(limit ?: POKEDEX_KANTO)
}