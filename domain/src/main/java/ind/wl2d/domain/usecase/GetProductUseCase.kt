package ind.wl2d.domain.usecase

import ind.wl2d.domain.repository.ProductRepository

class GetProductUseCase(
    private val productRepository: ProductRepository,
) {
    suspend fun invoke() = productRepository.getProducts()
}
