package ui

import domain.model.Pokemon
import domain.model.PokemonDetail
import domain.onFailure
import domain.onSuccess
import domain.use_cases.implementations.GetPokemonByIdUseCaseImpl
import domain.use_cases.implementations.GetPokemonsUseCaseImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import util.CoroutineViewModel
import util.UiState

class PokedexViewModel : CoroutineViewModel(), KoinComponent {
    private val getPokemonsUseCaseImpl: GetPokemonsUseCaseImpl by inject()
    private val getPokemonByIdUseCaseImpl: GetPokemonByIdUseCaseImpl by inject()

    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons = _pokemons.asStateFlow()

    private val _pokemonDetail = MutableStateFlow<UiState<PokemonDetail?>>(UiState.Idle)
    val pokemonDetail = _pokemonDetail.asStateFlow()

    private val _selectedPokemon = MutableStateFlow(1)
    val selectedPokemon = _selectedPokemon.asStateFlow()

    companion object {
        const val POKEDEX_KANTO = "151"
    }

    init {

        getPokemonList()
    }

    fun getPokemonList() {
        _pokemonDetail.value = UiState.Loading

        coroutineScope.launch {
            getPokemonsUseCaseImpl.invoke(limit = POKEDEX_KANTO)
                .onSuccess {
                    getPokemonDetail(selectedPokemon.value)
                    _pokemons.value = it
                }.onFailure {
                    _pokemonDetail.value =
                        UiState.Error(message = "Pokemons not found, please try again")
                }
        }
    }

    fun getPokemonDetail(id: Int) {
        _pokemonDetail.value = UiState.Loading

        _selectedPokemon.value = id
        coroutineScope.launch {
            getPokemonByIdUseCaseImpl.invoke(id)
                .onSuccess {
                    _pokemonDetail.value = UiState.Success(it)
                }.onFailure {
                    _pokemonDetail.value =
                        UiState.Error(message = "Pokemon not found, please try again")
                }
        }
    }
}