package ind.wl2d.data.repository

import ind.wl2d.domain.model.Product
import ind.wl2d.domain.network.NetworkService
import ind.wl2d.domain.network.ResultWrapper
import ind.wl2d.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val networkService: NetworkService,
) : ProductRepository {
    override suspend fun getProducts(): ResultWrapper<List<Product>> = networkService.getProducts()
}
