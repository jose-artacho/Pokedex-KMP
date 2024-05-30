package data.network

import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PokemonService: KtorApi() {

    suspend fun getPokemons(limit: String): PokemonResponse = client.get {
        pathUrl(PokemonServiceConstants.GET_POKEMONS)
        parameter(PokemonServiceConstants.LIMIT, limit)
    }.body()

    suspend fun getPokemonById(id: Int): PokemonDetailResponse = client.get {
        pathUrl(PokemonServiceConstants.GET_POKEMON_BY_ID + id)
    }.body()
}