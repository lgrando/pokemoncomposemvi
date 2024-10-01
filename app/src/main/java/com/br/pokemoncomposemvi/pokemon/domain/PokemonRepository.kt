package com.br.pokemoncomposemvi.pokemon.domain

import com.br.pokemoncomposemvi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getAllPokemons(forceRefresh: Boolean = false): Flow<Resource<out List<Pokemon>>>
}