package ind.wl2d.trueshop.ui.feature.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ind.wl2d.domain.model.Product
import org.koin.androidx.compose.koinViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    when (uiState.value) {
        is HomeScreenUIEvents.Loading -> {
            CircularProgressIndicator()
        }
        is HomeScreenUIEvents.Success -> {
            val data = (uiState.value as HomeScreenUIEvents.Success).data
            LazyColumn(
                modifier,
            ) {
                items(data) { product ->
                    ProductItem(product)
                }
            }
        }
        is HomeScreenUIEvents.Error -> {
            Text(text = (uiState.value as HomeScreenUIEvents.Error).message)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun ProductItem(product: Product) {
    Card(
        modifier = Modifier.padding(8.dp),
    ) {
        Row(
            modifier =
                Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = product.title, style = MaterialTheme.typography.titleLarge)
                Text(text = "$${product.price}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
