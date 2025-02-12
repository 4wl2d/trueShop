package ind.wl2d.data.models

import ind.wl2d.domain.model.Product
import kotlinx.serialization.Serializable

@Serializable
data class DataProductModel(
    val id: Long,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val image: String,
)

fun DataProductModel.toProduct(): Product =
    Product(
        id = id,
        title = title,
        price = price,
        category = category,
        description = description,
        image = image,
    )
