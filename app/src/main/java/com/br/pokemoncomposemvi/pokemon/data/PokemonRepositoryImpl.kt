package com.br.pokemoncomposemvi.pokemon.data

import com.br.pokemoncomposemvi.pokemon.domain.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonDataSource.Remote,
    private val localDataSource: PokemonDataSource.Local,
) : PokemonRepository {

    override suspend fun getAllPokemons(forceRefresh: Boolean) =
        if (forceRefresh) {
            remoteDataSource.getAllPokemon()
        } else {
            localDataSource.getAllPokemon()
        }
}

