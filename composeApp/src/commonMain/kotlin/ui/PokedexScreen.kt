package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import domain.model.Pokemon
import ui.PokedexScreen.Companion.IMAGE_EXTENSION
import ui.PokedexScreen.Companion.SPRITE_URL

class PokedexScreen : Screen {

    companion object {
        const val SPRITE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        const val IMAGE_EXTENSION = ".png"
    }

    @Composable
    override fun Content() {

        val viewModel = remember { PokedexViewModel() }
        val pokemons by viewModel.pokemons.collectAsState()
        var selectedPokemon by remember { mutableStateOf(1) }

        Column(
            modifier = Modifier
                .background(Color(0xFFc32c33))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopSection()
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black)
            Spacer(modifier = Modifier.height(32.dp))
            ScreenSection(selectedPokemon)
            Spacer(modifier = Modifier.height(32.dp))
            BottomSection(pokemons) {
                selectedPokemon = it
            }
        }

    }
}

@Composable
fun TopSection() {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().padding(vertical = 32.dp).padding(horizontal = 16.dp)
    ) {
        CircleButton(Color.Blue, 32)
        CircleButton(Color.Red)
        CircleButton(Color.Green)
        CircleButton(Color.Yellow)
    }
}

@Composable
fun ScreenSection(selectedPokemon: Int) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 32.dp),
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp)
    ) {
        val url = "$SPRITE_URL${selectedPokemon}$IMAGE_EXTENSION"

        Surface(
            modifier = Modifier.padding(32.dp),
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        ) {
            SubcomposeAsyncImage(
                model = url,
                contentDescription = null
            ) {
                val state = painter.state
                if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                    CircularProgressIndicator()
                } else {
                    SubcomposeAsyncImageContent()
                }
            }
            Text(
                text = "#$selectedPokemon",
                color = Color.White,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BottomSection(pokemons: List<Pokemon>, onClick: (Int) -> Unit) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {

        pokemons.forEach {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFF63a960)),
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 16.dp)
                    .clickable {
                        onClick(pokemons.indexOf(it) + 1)
                    }
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = it.name,
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Composable
fun CircleButton(color: Color, size: Int = 16) {
    Box(
        modifier = Modifier
            .size(size.dp)
            .background(color, shape = CircleShape)
    )
}