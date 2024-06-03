package data.repository

import data.datasource.PokemonDataSource
import fakedata.exception
import fakedata.fakePokemonDetailResponse
import fakedata.fakePokemonResponse
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class PokemonRepositoryImplTests {

    private val pokemonDataSource = mock<PokemonDataSource>()
    private val sut = PokemonRepositoryImpl(
        pokemonDataSource = pokemonDataSource
    )

    @Test
    fun `repository returns pokemon list success`() = runBlocking {

        everySuspend {
            pokemonDataSource.getPokemons("5")
        } returns Result.success(fakePokemonResponse)

        val result = sut.getPokemons("5")

        assertEquals(fakePokemonResponse, result.getOrNull())
    }

    @Test
    fun `repository returns pokemon list Error`() = runBlocking {

        everySuspend {
            pokemonDataSource.getPokemons("5")
        } returns Result.failure(exception)

        val result = sut.getPokemons("5")

        assertEquals(exception, result.exceptionOrNull())
    }

    @Test
    fun `repository returns pokemon detail success`() = runBlocking {

        everySuspend {
            pokemonDataSource.getPokemonById(6)
        } returns Result.success(fakePokemonDetailResponse)

        val result = sut.getPokemonById(6)

        assertEquals(fakePokemonDetailResponse, result.getOrNull())
    }

    @Test
    fun `repository returns pokemon detail Error`() = runBlocking {

        everySuspend {
            pokemonDataSource.getPokemonById(6)
        } returns Result.failure(exception)

        val result = sut.getPokemonById(6)

        assertEquals(exception, result.exceptionOrNull())
    }
}