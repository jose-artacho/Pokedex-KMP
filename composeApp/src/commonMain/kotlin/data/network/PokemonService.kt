package data.network

import data.datasource.entities.PokemonResponse
import io.ktor.client.call.body
import io.ktor.client.request.get

class PokemonService: KtorApi() {

    suspend fun getPokemons(): List<PokemonResponse> = client.get {
        pathUrl("pokemon/")
    }.body()

    suspend fun getPokemon(id: Int): PokemonResponse = client.get {
        pathUrl("pokemon/${id}")
    }.body()
}