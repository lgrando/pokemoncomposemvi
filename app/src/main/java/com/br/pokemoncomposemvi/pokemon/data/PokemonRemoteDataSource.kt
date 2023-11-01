package com.br.pokemoncomposemvi.pokemon.data

import com.br.pokemoncomposemvi.framework.extensions.remoteApiToFlow
import com.br.pokemoncomposemvi.framework.remote.PokemonApi
import javax.inject.Inject

class PokemonRemoteDataSource @Inject constructor(private val api: PokemonApi) :
    PokemonDataSource.Remote {

    override suspend fun getAllPokemon() =
        remoteApiToFlow({ api.getAllPokemons() }, { it.toListPokemon() })

}

