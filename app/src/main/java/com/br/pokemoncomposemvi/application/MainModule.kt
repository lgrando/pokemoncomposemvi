package com.br.pokemoncomposemvi.application

import com.br.pokemoncomposemvi.framework.remote.PokemonApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        val timeout: Long = 30

        val loggingInterceptor = HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder()
        client.readTimeout(timeout, TimeUnit.SECONDS)
        client.connectTimeout(timeout, TimeUnit.SECONDS)
        client.addInterceptor(loggingInterceptor)

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create(gson))

        return retrofit.build()
    }

    @Provides
    fun providePokemonApi(retrofit: Retrofit): PokemonApi = retrofit.create(PokemonApi::class.java)
}