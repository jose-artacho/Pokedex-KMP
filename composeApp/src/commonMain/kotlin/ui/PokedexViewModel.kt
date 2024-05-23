package ui

import domain.model.Pokemon
import domain.model.PokemonDetail
import domain.repository.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import util.CoroutineViewModel

class PokedexViewModel : CoroutineViewModel(), KoinComponent {
    private val recipeRepository: PokemonRepository by inject()

    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        coroutineScope.launch {
            _pokemons.value = recipeRepository.getPokemons()
        }
    }

    fun observePokemon(onChange: (List<Pokemon>) -> Unit) {
        pokemons.onEach {
            onChange(it)
        }.launchIn(coroutineScope)
    }
}