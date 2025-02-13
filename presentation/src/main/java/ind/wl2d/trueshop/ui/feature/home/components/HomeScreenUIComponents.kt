package ind.wl2d.trueshop.ui.feature.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import ind.wl2d.domain.model.Product
import ind.wl2d.trueshop.ui.theme.AppTypography
import ind.wl2d.trueshop.ui.theme.Block
import ind.wl2d.trueshop.ui.theme.newPeninimMtFontFamily

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeProductRow(
    modifier: Modifier = Modifier,
    products: List<Product>,
    title: String,
) {
    Column {
        Column {
            Text(text = title, fontStyle = AppTypography.titleLarge.fontStyle)
            LazyRow {
                items(products) { product ->
                    HomeProductCardItem(
                        modifier,
                        product = product,
                    )
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeProductCardItem(
    modifier: Modifier = Modifier,
    product: Product,
) {
    Card(
        modifier =
            modifier
                .width(126.dp)
                .height(142.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Block),
    ) {
        Column {
            Box(
                modifier.fillMaxWidth().height(98.dp),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = product.image,
                    contentDescription = "Image of product",
                    modifier.fillMaxSize(),
                )
            }
            Column(
                modifier.padding(4.dp),
            ) {
                Text(
                    product.title,
                    fontSize = 14.sp,
                    fontFamily = newPeninimMtFontFamily,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "$${product.price}",
                    fontSize = 12.sp,
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun HomeProductCardItemPreview(modifier: Modifier = Modifier) {
    HomeProductCardItem(
        modifier,
        Product(
            id = 0,
            title = "Watch",
            price = 40.0,
            category = "featured",
            description = "kekW",
            image = "https://fakestoreapi.com/img/71pWzhdJNwL._AC_UL640_QL65_ML3_.jpg",
        ),
    )
}
