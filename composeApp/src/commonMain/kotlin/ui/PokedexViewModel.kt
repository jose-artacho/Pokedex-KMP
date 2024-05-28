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

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    init {
        getPokemonList()
        getPokemonDetail(1)
    }

    private fun getPokemonList() {
        coroutineScope.launch {
            _pokemons.value = recipeRepository.getPokemons()
        }
    }

    fun getPokemonDetail(id: Int) {
        coroutineScope.launch {
            _pokemonDetail.value = recipeRepository.getPokemonById(id)
        }
    }
}