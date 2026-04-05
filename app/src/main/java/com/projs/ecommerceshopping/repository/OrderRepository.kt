package com.projs.ecommerceshopping.repository

import androidx.lifecycle.LiveData
import com.projs.ecommerceshopping.model.local.OrderDao
import com.projs.ecommerceshopping.model.local.OrderEntity
import com.projs.ecommerceshopping.model.local.OrderItemEntity

class OrderRepository(private val dao: OrderDao) : IOrderRepository {

    override suspend fun placeOrder(order: OrderEntity, items: List<OrderItemEntity>) {
        val orderId = dao.insertOrder(order)

        val orderItems = items.map {
            it.copy(orderId = orderId.toInt())
        }

        dao.insertOrderItems(orderItems)
    }

    override fun getOrders(): LiveData<List<OrderEntity>> {
        return dao.getOrders()
    }
}