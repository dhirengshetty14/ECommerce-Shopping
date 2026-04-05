package com.projs.ecommerceshopping.repository

import androidx.lifecycle.LiveData
import com.projs.ecommerceshopping.model.local.CartItem

interface ICartRepository {

    val allItems: LiveData<List<CartItem>>

    suspend fun insert(item: CartItem)

    suspend fun update(item: CartItem)

    suspend fun delete(item: CartItem)

    suspend fun clearCart()
}