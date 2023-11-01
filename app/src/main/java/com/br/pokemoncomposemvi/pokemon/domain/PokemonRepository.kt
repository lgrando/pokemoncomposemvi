package com.br.pokemoncomposemvi.pokemon.domain

import com.br.pokemoncomposemvi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getAllPokemons(): Flow<Resource<out List<Pokemon>>>
}