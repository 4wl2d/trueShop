package ind.wl2d.trueshop.ui.feature.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ind.wl2d.domain.model.Product
import ind.wl2d.domain.network.ResultWrapper
import ind.wl2d.domain.usecase.GetProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductUseCase: GetProductUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeScreenUIEvents>(HomeScreenUIEvents.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading
            val featuredProducts = getProducts("electronics")
            val popularProducts = getProducts("jewelery")

            if (featuredProducts.isEmpty() && popularProducts.isEmpty()) {
                _uiState.value = HomeScreenUIEvents.Error("Failed to load products")
                return@launch
            }

            _uiState.value = HomeScreenUIEvents.Success(featuredProducts, popularProducts)
        }
    }

    private suspend fun getProducts(category: String?): List<Product> {
        getProductUseCase.invoke(category).let { result ->
            when (result) {
                is ResultWrapper.Success -> {
                    return result.value
                }
                is ResultWrapper.Failure -> {
                    return emptyList()
                }
            }
        }
    }
}

sealed class HomeScreenUIEvents {
    object Loading : HomeScreenUIEvents()

    data class Success(
        val featuredProducts: List<Product>,
        val popularProducts: List<Product>,
    ) : HomeScreenUIEvents()

    data class Error(
        val message: String,
    ) : HomeScreenUIEvents()
}
