package com.br.pokemoncomposemvi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.br.pokemoncomposemvi.pokemon.list.presentation.PokemonListScreen
import com.br.pokemoncomposemvi.ui.theme.PokemonComposeMVITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonComposeMVITheme {
                PokemonListScreen()
            }
        }
    }
}