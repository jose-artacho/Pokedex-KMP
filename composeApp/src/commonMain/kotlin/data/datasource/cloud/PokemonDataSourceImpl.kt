package data.datasource.cloud

import data.datasource.PokemonDataSource
import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResponse
import data.network.KtorApi
import data.network.PokemonServiceConstants
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.withContext
import util.Dispatcher

internal class PokemonDataSourceImpl(
    private val api: KtorApi
) : PokemonDataSource {

    override suspend fun getPokemons(limit: String): Result<PokemonResponse> =
        api.getApiCall(PokemonServiceConstants.GET_POKEMONS, mapOf(PokemonServiceConstants.LIMIT to limit))

    override suspend fun getPokemonById(id: Int): Result<PokemonDetailResponse> =
        api.getApiCall(PokemonServiceConstants.GET_POKEMON_BY_ID.plus(id))
}