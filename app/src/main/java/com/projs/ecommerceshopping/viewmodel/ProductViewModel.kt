package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.projs.ecommerceshopping.model.response.Product
import com.projs.ecommerceshopping.repository.IProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: IProductRepository
) : ViewModel() {

    val products = MutableLiveData<List<Product>>()
    val error = MutableLiveData<String>()

    fun fetchProducts(subCategoryId: String) {
        viewModelScope.launch {
            try {
                val response = repository.getProducts(subCategoryId)
                if (response.status == 0) {
                    products.value = response.products
                } else {
                    error.value = response.message
                }
            } catch (e: Exception) {
                error.value = e.message
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