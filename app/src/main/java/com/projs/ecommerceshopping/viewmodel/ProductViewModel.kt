package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.projs.ecommerceshopping.model.response.Product
import com.projs.ecommerceshopping.model.response.ProductDetails
import com.projs.ecommerceshopping.repository.IProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: IProductRepository
) : ViewModel() {

    val products = MutableLiveData<List<Product>>()
    val error = MutableLiveData<String>()
    val searchResults = MutableLiveData<List<Product>>()

    val productDetails = MutableLiveData<ProductDetails>()

    private var fullList: List<Product> = emptyList()

    fun fetchProducts(subCategoryId: String) {
        viewModelScope.launch {
            try {
                val response = repository.getProducts(subCategoryId)
                if (response.status == 0) {
                    products.value = response.products
                    fullList=response.products
                    searchResults.value=response.products
                } else {
                    error.value = response.message
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun fetchProductDetails(productId: String) {
        viewModelScope.launch {
            try {
                val response = repository.getProductDetails(productId)
                if (response.status == 0) {
                    productDetails.value = response.product
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            try {

                if (query.isEmpty()) {
                    searchResults.value = fullList
                    return@launch
                }
                if (query.length < 2) {
                    searchResults.value = fullList
                    return@launch
                }
                val response = repository.searchProduct(query)

                if (response.status == 0 && response.product != null) {
                    searchResults.value = listOf(response.product)
                } else {
                    val filtered = fullList.filter {
                        it.product_name.contains(query, ignoreCase = true)
                    }
                    searchResults.value = filtered
                }

            } catch (e: Exception) {
                searchResults.value = fullList
            }
        }
    }
}

class ProductViewModelFactory(
    private val repository: IProductRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel(repository) as T
    }
}