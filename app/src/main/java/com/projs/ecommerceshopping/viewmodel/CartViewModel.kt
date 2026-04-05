package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.projs.ecommerceshopping.model.local.CartItem
import com.projs.ecommerceshopping.repository.ICartRepository
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: ICartRepository
) : ViewModel() {

    val cartItems = repository.allItems

    fun insert(item: CartItem) = viewModelScope.launch {
        repository.insert(item)
    }

    fun update(item: CartItem) = viewModelScope.launch {
        repository.update(item)
    }

    fun delete(item: CartItem) = viewModelScope.launch {
        repository.delete(item)
    }
}

class CartViewModelFactory(
    private val repository: ICartRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartViewModel(repository) as T
    }
}