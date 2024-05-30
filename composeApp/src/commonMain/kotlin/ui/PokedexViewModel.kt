package ui

import domain.model.Pokemon
import domain.model.PokemonDetail
import domain.use_cases.GetPokemonByIdUseCase
import domain.use_cases.GetPokemonsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import util.CoroutineViewModel

class PokedexViewModel : CoroutineViewModel(), KoinComponent {
    private val getPokemonsUseCase: GetPokemonsUseCase by inject()
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase by inject()

    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons: StateFlow<List<Pokemon>> = _pokemons

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    companion object {
        const val POKEDEX_KANTO = "151"
    }

    init {
        getPokemonList()
        getPokemonDetail(1)
    }

    private fun getPokemonList() {
        coroutineScope.launch {
            _pokemons.value = getPokemonsUseCase.invoke(limit = POKEDEX_KANTO)
        }
    }

    fun getPokemonDetail(id: Int) {
        coroutineScope.launch {
            _pokemonDetail.value = getPokemonByIdUseCase.invoke(id)
        }
    }
}