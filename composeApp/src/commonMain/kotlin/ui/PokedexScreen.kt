package ui

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import domain.model.Pokemon
import domain.model.PokemonDetail
import ui.PokedexScreen.Companion.IMAGE_EXTENSION
import ui.PokedexScreen.Companion.PAGE_DESCRIPTION
import ui.PokedexScreen.Companion.PAGE_IMAGE
import ui.PokedexScreen.Companion.SPRITE_URL
import util.UiState

class PokedexScreen : Screen {

    companion object {
        const val SPRITE_URL =
            "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"
        const val IMAGE_EXTENSION = ".png"
        const val PAGE_IMAGE = 0
        const val PAGE_DESCRIPTION = 1
    }

    @Composable
    override fun Content() {

        val viewModel = remember { PokedexViewModel() }
        val pokemons by viewModel.pokemons.collectAsState()
        val pokemonDetail by viewModel.pokemonDetail.collectAsState()
        var selectedPokemon by remember { mutableStateOf(1) }

        Column(
            modifier = Modifier
                .background(Color(0xFFc32c33))
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally
        ) {
            TopSection()
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(32.dp))
            ScreenSection(selectedPokemon, pokemonDetail)
            Spacer(modifier = Modifier.height(32.dp))
            PokemonListSection(pokemons) {
                selectedPokemon = it
                viewModel.getPokemonDetail(selectedPokemon)
            }
        }

    }
}

@Composable
fun TopSection() {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth().padding(top = 48.dp, bottom = 16.dp)
            .padding(horizontal = 16.dp)
    ) {
        CircleButton(Color.Blue, 32)
        CircleButton(Color.Red)
        CircleButton(Color.Green)
        CircleButton(Color.Yellow)
    }
}

@Composable
fun ScreenSection(selectedPokemon: Int, pokemonDetail: UiState<PokemonDetail?>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 24.dp),
        color = Color.LightGray,
        shape = RoundedCornerShape(8.dp)
    ) {
        Surface(
            modifier = Modifier.padding(32.dp).fillMaxWidth(),
            color = Color.Black,
            shape = RoundedCornerShape(8.dp)
        ) {
            when (pokemonDetail) {
                is UiState.Success -> {
                    ScreenViewPager(selectedPokemon, pokemonDetail.data)
                }

                is UiState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(24.dp),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = pokemonDetail.message,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
                }

                else -> {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(32.dp),
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScreenViewPager(selectedPokemon: Int, pokemonDetail: PokemonDetail?) {
    val pagerState = rememberPagerState(pageCount = { 2 })
    val url = "$SPRITE_URL${selectedPokemon}$IMAGE_EXTENSION"

    Column {
        HorizontalPager(
            modifier = Modifier.height(200.dp).fillMaxSize().align(CenterHorizontally),
            state = pagerState
        ) { page ->

            when (page) {
                PAGE_IMAGE -> ImageViewPage(url)

                PAGE_DESCRIPTION -> DescriptionViewPage(pokemonDetail)
            }
        }

        ViewPagerBottomSection(pagerState, selectedPokemon)
    }
}

@Composable
fun ImageViewPage(url: String) {
    SubcomposeAsyncImage(
        model = url,
        contentDescription = null
    ) {
        val state = painter.state
        if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))

                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
            }
        } else {
            SubcomposeAsyncImageContent(
                modifier = Modifier.fillMaxSize().size(180.dp),
                alignment = Center
            )
        }
    }
}

@Composable
fun DescriptionViewPage(pokemonDetail: PokemonDetail?) {
    Column {
        Text(
            modifier = Modifier.verticalScroll(rememberScrollState())
                .fillMaxSize().padding(16.dp).weight(1.0f),
            text = pokemonDetail?.description ?: "",
            color = Color.White,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ViewPagerBottomSection(pagerState: PagerState, selectedPokemon: Int) {
    Row(
        Modifier.fillMaxWidth().height(16.dp).padding(horizontal = 16.dp),
        verticalAlignment = CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(0.5f),
            text = "#$selectedPokemon",
            color = Color.White,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.bodyMedium,
        )

        Spacer(modifier = Modifier.weight(0.5f))

        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) Color.LightGray else Color.DarkGray
            Box(
                modifier = Modifier.align(CenterVertically)
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(8.dp)
            )
        }

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun PokemonListSection(pokemons: List<Pokemon>, onClick: (Int) -> Unit) {

    var selectedPokemon by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {

        pokemons.forEach {
            ElevatedCard(
                elevation = CardDefaults.cardElevation(6.dp),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(
                    containerColor =
                    if (selectedPokemon == pokemons.indexOf(it) + 1) {
                        Color(0xFF477f44)
                    } else {
                        Color(0xFF63a960)
                    }
                ),
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 24.dp)
                    .clickable {
                        selectedPokemon = pokemons.indexOf(it) + 1
                        onClick(selectedPokemon)

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