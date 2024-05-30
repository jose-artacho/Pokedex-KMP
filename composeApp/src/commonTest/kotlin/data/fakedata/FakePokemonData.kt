package data.fakedata

import data.datasource.entities.PokemonResponse
import data.datasource.entities.PokemonResultResponse


internal val fakeBaseUrl = "http://test.com"
internal val fakePokemonId = "25"

internal val fakePokemonResultResponse = PokemonResultResponse(
    name = "test",
    url = "test.com"
)

internal val fakePokemonResponse = PokemonResponse(
     count = 2,
     next = "test 3",
     previous = "test 1",
     results = listOf(fakePokemonResultResponse)
)

internal val exceptionMessage = "test error exception"

internal val exception = Exception(exceptionMessage)