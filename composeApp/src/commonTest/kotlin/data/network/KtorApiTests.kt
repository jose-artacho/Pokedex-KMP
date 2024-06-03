package data.network

import data.datasource.entities.PokemonDetailResponse
import data.datasource.entities.PokemonResponse
import fakedata.fakePokemonDetailResponse
import fakedata.fakePokemonResponse
import fakedata.mockSuccessPokemonDetail
import fakedata.mockSuccessPokemonList
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class KtorApiTests {

    @Test
    fun `Api client should return PokemonResponse`() = runBlocking {

        val apiClient = KtorApi(mockSuccessPokemonList)

        val result = apiClient.getApiCall<PokemonResponse>("/", emptyMap())

        assertEquals(fakePokemonResponse, result.getOrNull())
    }

    @Test
    fun `Api client should return PokemonDetailResponse`() = runBlocking {

        val apiClient = KtorApi(mockSuccessPokemonDetail)

        val result = apiClient.getApiCall<PokemonDetailResponse>("/", emptyMap())

        assertEquals(fakePokemonDetailResponse, result.getOrNull())
    }
}