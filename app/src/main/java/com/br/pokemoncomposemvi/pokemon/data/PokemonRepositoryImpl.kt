package com.br.pokemoncomposemvi.pokemon.data

import com.br.pokemoncomposemvi.pokemon.domain.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val remoteDataSource: PokemonDataSource.Remote
) : PokemonRepository {

    override suspend fun getAllPokemons() = remoteDataSource.getAllPokemon()
}

