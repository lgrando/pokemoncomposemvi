package com.br.pokemoncomposemvi.pokemon.data

import com.google.gson.annotations.SerializedName

data class PokemonListDto(
    @SerializedName("results") val results: List<ResultDto>
)

data class ResultDto(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)