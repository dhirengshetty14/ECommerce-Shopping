package com.projs.ecommerceshopping.repository

import androidx.lifecycle.LiveData
import com.projs.ecommerceshopping.model.local.OrderEntity
import com.projs.ecommerceshopping.model.local.OrderItemEntity

interface IOrderRepository {
    suspend fun placeOrder(order: OrderEntity, items: List<OrderItemEntity>)
    fun getOrders(): LiveData<List<OrderEntity>>
}