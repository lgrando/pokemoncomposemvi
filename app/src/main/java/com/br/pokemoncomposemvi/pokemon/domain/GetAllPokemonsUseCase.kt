package com.br.pokemoncomposemvi.pokemon.domain

import javax.inject.Inject

class GetAllPokemonsUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(forceRefresh: Boolean = false) =
        repository.getAllPokemons(forceRefresh = forceRefresh)
}