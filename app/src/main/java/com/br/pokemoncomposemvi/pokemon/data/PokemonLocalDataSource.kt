package com.br.pokemoncomposemvi.pokemon.data

import com.br.pokemoncomposemvi.pokemon.domain.Pokemon
import com.br.pokemoncomposemvi.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonLocalDataSource @Inject constructor() : PokemonDataSource.Local {

    override suspend fun getAllPokemon() = flow {
        emit(Resource.Loading(isLoading = true))
        delay(1000L)
        emit(Resource.Success(data = mockLocalPokemons))
        emit(Resource.Loading(isLoading = false))
    }

    private companion object {
        val mockLocalPokemons = listOf(Pokemon("Charmander"))
    }

}

