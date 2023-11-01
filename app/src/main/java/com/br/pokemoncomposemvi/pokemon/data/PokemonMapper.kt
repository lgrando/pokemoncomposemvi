package com.br.pokemoncomposemvi.pokemon.data

import com.br.pokemoncomposemvi.pokemon.domain.Pokemon

fun PokemonListDto.toListPokemon() = this.results.map { it.toPokemon() }

fun ResultDto.toPokemon() = Pokemon(name = this.name)