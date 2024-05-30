package ui

import domain.model.Pokemon
import domain.model.PokemonDetail
import domain.onFailure
import domain.onSuccess
import domain.use_cases.implementations.GetPokemonByIdUseCaseImpl
import domain.use_cases.implementations.GetPokemonsUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import util.CoroutineViewModel

class PokedexViewModel : CoroutineViewModel(), KoinComponent {
    private val getPokemonsUseCaseImpl: GetPokemonsUseCaseImpl by inject()
    private val getPokemonByIdUseCaseImpl: GetPokemonByIdUseCaseImpl by inject()

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
            getPokemonsUseCaseImpl.invoke(limit = POKEDEX_KANTO)
                .onSuccess {
                    _pokemons.value = it
                }.onFailure {

                }
        }
    }

    fun getPokemonDetail(id: Int) {
        coroutineScope.launch {
            getPokemonByIdUseCaseImpl.invoke(id)
                .onSuccess {
                    _pokemonDetail.value = it
                }.onFailure {

                }
        }
    }
}