package fakedata

import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf

val mockSuccessPokemonList = MockEngine {
    respond(
        content = pokemonListJsonResponse.trimIndent(),
        status = HttpStatusCode.OK,
        headers = headersOf("Content-Type", "application/json")
    )
}

val mockSuccessPokemonDetail = MockEngine {
    respond(
        content = pokemonDetailJsonResponse.trimIndent(),
        status = HttpStatusCode.OK,
        headers = headersOf("Content-Type", "application/json")
    )
}

val mockErrorEngine = MockEngine {
    respond(
        content = exception.message ?: "",
        headers = headersOf("Content-Type", "application/json")
    )
}

internal const val exceptionMessage = "test error exception"

internal val exception = Exception(exceptionMessage)