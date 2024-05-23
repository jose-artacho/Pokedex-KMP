package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen

class PokedexScreen : Screen {

    @Composable
    override fun Content() {

        val viewModel = remember { PokedexViewModel() }
        val pokemons by viewModel.pokemons.collectAsState()

        val scrolling = Modifier
            .verticalScroll(rememberScrollState())


        Column(
            modifier = scrolling.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            pokemons.forEach {
                println(it.name)
            }
        }
    }
}