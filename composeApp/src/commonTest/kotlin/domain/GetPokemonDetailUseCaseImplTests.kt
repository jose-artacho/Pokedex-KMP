package domain

import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import domain.repository.PokemonRepository
import domain.use_cases.implementations.GetPokemonByIdUseCaseImpl
import fakedata.exception
import fakedata.fakePokemonDetail
import fakedata.fakePokemonDetailResponse
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class GetPokemonDetailUseCaseImplTests {

    private val repository = mock<PokemonRepository>()

    private val sut = GetPokemonByIdUseCaseImpl(repository)

    @Test
    fun `Use case returns pokemon detail success`() = runBlocking {

        everySuspend {
            repository.getPokemonById(6)
        } returns Result.success(fakePokemonDetailResponse)

        val actual = sut(6)

        assertEquals(fakePokemonDetail, actual.value)
    }

    @Test
    fun `Use case returns pokemon detail Error`() = runBlocking {

        everySuspend {
            repository.getPokemonById(6)
        } returns Result.failure(exception)

        val actual = sut(6)

        assertEquals(exception, actual.exceptionOrNull())
    }
}