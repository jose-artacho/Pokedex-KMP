package data.util

import data.util.asExternalModel
import domain.model.Pokemon
import fakedata.fakePokemonDetail
import fakedata.fakePokemonDetailResponse
import fakedata.fakePokemonResponse
import fakedata.fakePokemons
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

class MappersTests {

    @Test
    fun `pokemon response map success`() = runBlocking {

        // Given
        val pokemonList = fakePokemonResponse.results
        // When
        val sut: List<Pokemon> = pokemonList.map {
            it.asExternalModel()
        }
        // Then
        assertEquals(sut, fakePokemons)
    }

    @Test
    fun `pokemon detail response map success`() = runBlocking {

        // When
        val result = fakePokemonDetailResponse.asExternalModel()

        // Then
        assertEquals(result, fakePokemonDetail)
    }
}