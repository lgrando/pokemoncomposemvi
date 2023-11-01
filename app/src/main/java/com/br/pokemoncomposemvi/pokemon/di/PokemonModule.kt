package com.br.pokemoncomposemvi.pokemon.di

import com.br.pokemoncomposemvi.pokemon.data.PokemonDataSource
import com.br.pokemoncomposemvi.pokemon.data.PokemonRemoteDataSource
import com.br.pokemoncomposemvi.pokemon.data.PokemonRepositoryImpl
import com.br.pokemoncomposemvi.pokemon.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PokemonModule {

    @Binds
    abstract fun bindPokemonRepository(pokemonRepositoryImpl: PokemonRepositoryImpl): PokemonRepository

    @Binds
    abstract fun bindPokemonRemoteDataSource(pokemonRemoteDataSource: PokemonRemoteDataSource): PokemonDataSource.Remote
}