package com.br.pokemoncomposemvi.pokemon.data

import com.br.pokemoncomposemvi.pokemon.domain.Pokemon
import com.br.pokemoncomposemvi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonDataSource {

    interface Remote {
        suspend fun getAllPokemon(): Flow<Resource<out List<Pokemon>>>
    }

    interface Local {
        suspend fun getAllPokemon(): Flow<Resource<out List<Pokemon>>>
    }
}