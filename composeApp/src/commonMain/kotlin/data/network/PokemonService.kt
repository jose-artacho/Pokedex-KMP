package data.network

import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class PokemonService: KtorApi() {

    suspend fun getPokemons(): PokemonResponse = client.get {
        pathUrl("api/v2/pokemon")
        parameter("limit", "151")
    }.body()

    suspend fun getPokemonById(id: Int): PokemonDetailResponse = client.get {
        pathUrl("api/v2/pokemon-species/${id}")
    }.body()
}