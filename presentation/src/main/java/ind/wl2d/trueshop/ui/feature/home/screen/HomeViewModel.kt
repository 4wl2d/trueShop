package ind.wl2d.trueshop.ui.feature.home.screen

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
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            _uiState.value = HomeScreenUIEvents.Loading

            getProductUseCase.invoke().let { result ->
                when (result) {
                    is ResultWrapper.Success -> {
                        val data = result.value
                        _uiState.value = HomeScreenUIEvents.Success(data)
                    }
                    is ResultWrapper.Failure -> {
                        val error = result.exception.message ?: "Error occurred!"
                        _uiState.value = HomeScreenUIEvents.Error(error)
                    }
                }
            }
        }
    }
}

sealed class HomeScreenUIEvents {
    object Loading : HomeScreenUIEvents()

    data class Success(
        val data: List<Product>,
    ) : HomeScreenUIEvents()

    data class Error(
        val message: String,
    ) : HomeScreenUIEvents()
}
