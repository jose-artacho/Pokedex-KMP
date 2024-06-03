package domain

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import domain.repository.PokemonRepository
import domain.use_cases.implementations.GetPokemonsUseCaseImpl
import fakedata.exception
import fakedata.fakePokemonResponse
import fakedata.fakePokemons
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetPokemonsUseCaseImplTests {

    private val repository = mock<PokemonRepository>()

    private val sut = GetPokemonsUseCaseImpl(repository)

    @Test
    fun `Use case returns pokemon list success`() = runBlocking {

        everySuspend {
            repository.getPokemons("5")
        } returns Result.success(fakePokemonResponse)

        val actual = sut("5")

        assertEquals(fakePokemons, actual.value)
    }

    @Test
    fun `Use case returns pokemon list Error`() = runBlocking {

        everySuspend {
            repository.getPokemons("5")
        } returns Result.failure(exception)

        val actual = sut("5")

        assertEquals(exception, actual.exceptionOrNull())
    }
}