package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.*
import com.projs.ecommerceshopping.model.local.*
import com.projs.ecommerceshopping.repository.IOrderRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class OrderViewModel(private val repo: IOrderRepository) : ViewModel() {

    val orders = repo.getOrders()

    fun placeOrder(order: OrderEntity, items: List<OrderItemEntity>) {
        viewModelScope.launch {
            repo.placeOrder(order, items)
        }
    }
}

class OrderViewModelFactory(private val repo: IOrderRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return OrderViewModel(repo) as T
    }
}