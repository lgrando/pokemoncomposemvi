package com.br.pokemoncomposemvi.pokemon.list.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.br.pokemoncomposemvi.pokemon.domain.GetAllPokemonsUseCase
import com.br.pokemoncomposemvi.pokemon.domain.Pokemon
import com.br.pokemoncomposemvi.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonListViewModel @Inject constructor(
    private val getAllPokemonsUseCase: GetAllPokemonsUseCase
) : ViewModel() {

    private val intentChannel = Channel<PokemonListIntent>(Channel.UNLIMITED)

    private val _state = MutableStateFlow(PokemonListState())
    val state: StateFlow<PokemonListState> = _state.asStateFlow()

    init {
        Log.d("=-=-=-=-=", "init view model")
        handleIntents()
        sendIntent(PokemonListIntent.GetPokemons)
    }

    fun sendIntent(intent: PokemonListIntent) {
        viewModelScope.launch { intentChannel.send(intent) }
    }

    private fun handleIntents() {
        intentChannel.consumeAsFlow().onEach { intent ->
            when (intent) {
                PokemonListIntent.GetPokemons -> getAllPokemons()
            }
        }.launchIn(viewModelScope)
    }

    private fun getAllPokemons() {
        viewModelScope.launch {
            getAllPokemonsUseCase().collect { resource ->
                when (resource) {
                    is Resource.Error -> _state.update { it.copy(error = resource.error) }
                    is Resource.Loading -> _state.update { it.copy(isLoading = resource.isLoading) }
                    is Resource.Success -> _state.update {
                        it.copy(
                            pokemons = resource.data ?: listOf()
                        )
                    }
                }
            }
        }
    }
}

data class PokemonListState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val pokemons: List<Pokemon> = listOf()
)

sealed class PokemonListIntent {
    object GetPokemons : PokemonListIntent()
}