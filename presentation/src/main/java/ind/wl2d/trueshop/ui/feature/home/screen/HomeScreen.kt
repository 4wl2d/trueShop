package ind.wl2d.trueshop.ui.feature.home.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ind.wl2d.domain.model.Product
import ind.wl2d.trueshop.ui.feature.home.components.HomeProductRow
import ind.wl2d.trueshop.ui.feature.home.viewmodel.HomeScreenUIEvents
import ind.wl2d.trueshop.ui.feature.home.viewmodel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    Scaffold {
        Surface(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(it),
        ) {
            when (uiState.value) {
                is HomeScreenUIEvents.Loading -> {
                    CircularProgressIndicator()
                }
                is HomeScreenUIEvents.Success -> {
                    val data = (uiState.value as HomeScreenUIEvents.Success)
                    HomeContent(modifier, data.featuredProducts, data.popularProducts)
                }
                is HomeScreenUIEvents.Error -> {
                    Text(text = (uiState.value as HomeScreenUIEvents.Error).message)
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    featured: List<Product>,
    popular: List<Product>,
) {
    LazyColumn {
        item {
            if (featured.isNotEmpty()) {
                HomeProductRow(modifier, featured, "Featured")
                Spacer(modifier.size(16.dp))
            }
            if (popular.isNotEmpty()) {
                HomeProductRow(modifier, popular, "Popular")
            }
        }
    }
}
