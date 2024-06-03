package fakedata

import data.datasource.entities.PokemonResponse
import data.datasource.entities.PokemonResultResponse
import domain.model.Pokemon

internal val fakePokemonResponse = PokemonResponse(
     count = 5,
     next = "https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20",
     previous = null,
     results = listOf(
         PokemonResultResponse(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
         PokemonResultResponse(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/"),
         PokemonResultResponse(name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/"),
         PokemonResultResponse(name = "charmander", url = "https://pokeapi.co/api/v2/pokemon/4/"),
         PokemonResultResponse(name = "charmeleon", url = "https://pokeapi.co/api/v2/pokemon/5/")
     )
)

const val pokemonListJsonResponse = """
    {
    "count":5,"next":"https://pokeapi.co/api/v2/pokemon/?offset=20&limit=20",
    "previous":null,
    "results":[
    {"name":"bulbasaur","url":"https://pokeapi.co/api/v2/pokemon/1/"},
    {"name":"ivysaur","url":"https://pokeapi.co/api/v2/pokemon/2/"},
    {"name":"venusaur","url":"https://pokeapi.co/api/v2/pokemon/3/"},
    {"name":"charmander","url":"https://pokeapi.co/api/v2/pokemon/4/"},
    {"name":"charmeleon","url":"https://pokeapi.co/api/v2/pokemon/5/"}
    ]
    }
"""

internal val fakePokemons = listOf(
    Pokemon(name = "bulbasaur", url = "https://pokeapi.co/api/v2/pokemon/1/"),
    Pokemon(name = "ivysaur", url = "https://pokeapi.co/api/v2/pokemon/2/"),
    Pokemon(name = "venusaur", url = "https://pokeapi.co/api/v2/pokemon/3/"),
    Pokemon(name = "charmander", url = "https://pokeapi.co/api/v2/pokemon/4/"),
    Pokemon(name = "charmeleon", url = "https://pokeapi.co/api/v2/pokemon/5/")
)