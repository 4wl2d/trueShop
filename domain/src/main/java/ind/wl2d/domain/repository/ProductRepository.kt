package ind.wl2d.domain.repository

import ind.wl2d.domain.model.Product
import ind.wl2d.domain.network.ResultWrapper

interface ProductRepository {
    suspend fun getProducts(category: String?): ResultWrapper<List<Product>>
}
