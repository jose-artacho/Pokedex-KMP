package data.datasource

import data.datasource.cloud.PokemonDataSourceImpl
import fakedata.mockErrorEngine
import fakedata.mockSuccessPokemonDetail
import fakedata.mockSuccessPokemonList
import data.network.KtorApi
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PokemonDataSourceImplTests {

    @Test
    fun `getPokemons should return success with a list of 5 Pokemon`() = runBlocking {

        // Given
        val limit = "5"
        val apiClient = KtorApi(mockSuccessPokemonList)
        val sut = PokemonDataSourceImpl(apiClient)

        // When
        val result = sut.getPokemons(limit = limit)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull()?.results?.size.toString(), limit)
    }

    @Test
    fun `getPokemons should return an error`() = runBlocking {

        // Given
        val limit = "3"
        val apiClient = KtorApi(mockErrorEngine)
        val sut = PokemonDataSourceImpl(apiClient)

        // When
        val result = sut.getPokemons(limit = limit)

        // Then
        assertTrue(result.isFailure)
    }


    @Test
    fun `getPokemonById should return success with PokemonDetail`() = runBlocking {

        // Given
        val pokemonId = 6
        val apiClient = KtorApi(mockSuccessPokemonDetail)
        val sut = PokemonDataSourceImpl(apiClient)

        // When
        val result = sut.getPokemonById(id = pokemonId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(result.getOrNull()?.id, pokemonId)
    }

    @Test
    fun `getPokemonById should return an error`() = runBlocking {

        // Given
        val pokemonId = 6
        val apiClient = KtorApi(mockErrorEngine)
        val sut = PokemonDataSourceImpl(apiClient)

        // When
        val result = sut.getPokemonById(id = pokemonId)

        // Then
        assertTrue(result.isFailure)
    }
}