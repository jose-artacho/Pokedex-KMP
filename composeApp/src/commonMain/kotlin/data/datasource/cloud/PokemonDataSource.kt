package data.datasource.cloud

import data.network.PokemonService
import kotlinx.coroutines.withContext
import util.Dispatcher

class PokemonDataSource(
    private val api: PokemonService,
    private val dispatcher: Dispatcher

) {

    suspend fun  getPokemons() = withContext(dispatcher.ioDispatcher) {
        api.getPokemons()
    }
    suspend fun  getPokemonById(id: Int) = withContext(dispatcher.ioDispatcher) {
        api.getPokemonById(id)
    }
}