package com.projs.ecommerceshopping.repository

import androidx.lifecycle.LiveData
import com.projs.ecommerceshopping.model.local.CartDao
import com.projs.ecommerceshopping.model.local.CartItem

class CartRepository(private val dao: CartDao) : ICartRepository {

    override val allItems: LiveData<List<CartItem>> = dao.getAllItems()

    override suspend fun insert(item: CartItem) {
        dao.insert(item)
    }

    override suspend fun update(item: CartItem) {
        dao.update(item)
    }

    override suspend fun delete(item: CartItem) {
        dao.delete(item)
    }

    override suspend fun clearCart() {
        dao.clearCart()
    }
}