import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import ui.PokedexScreen

@Composable
fun App() {
    MaterialTheme {

        Navigator(screen = PokedexScreen())
    }
}