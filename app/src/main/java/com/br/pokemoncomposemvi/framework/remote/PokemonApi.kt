package com.br.pokemoncomposemvi.framework.remote

import com.br.pokemoncomposemvi.pokemon.data.PokemonListDto
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {

    @GET("pokemon")
    suspend fun getAllPokemons(): Response<PokemonListDto>
}