package ind.wl2d.domain.network

import ind.wl2d.domain.model.Product

interface NetworkService {
    suspend fun getProducts(category: String?): ResultWrapper<List<Product>>
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(
        val value: T,
    ) : ResultWrapper<T>()

    data class Failure(
        val exception: Exception,
    ) : ResultWrapper<Nothing>()
}
