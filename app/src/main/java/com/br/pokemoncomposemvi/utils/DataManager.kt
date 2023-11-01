package com.br.pokemoncomposemvi.utils

sealed class Resource<T> {
    class Loading(val isLoading: Boolean) : Resource<Nothing>()
    class Error(val error: String? = null) : Resource<Nothing>()
    class Success<T>(val data: T?) : Resource<T>()
}