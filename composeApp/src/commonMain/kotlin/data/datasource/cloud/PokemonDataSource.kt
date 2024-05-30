package data.datasource.cloud

import data.network.PokemonService
import kotlinx.coroutines.withContext
import util.Dispatcher

class PokemonDataSource(
    private val api: PokemonService,
    private val dispatcher: Dispatcher

) {

    suspend fun  getPokemons(limit: String) = withContext(dispatcher.ioDispatcher) {
        api.getPokemons(limit)
    }
    suspend fun  getPokemonById(id: Int) = withContext(dispatcher.ioDispatcher) {
        api.getPokemonById(id)
    }
}