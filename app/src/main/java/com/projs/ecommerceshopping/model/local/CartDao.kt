package com.projs.ecommerceshopping.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: CartItem)

    @Query("SELECT * FROM cart_items")
    fun getAllItems(): LiveData<List<CartItem>>

    @Update
    suspend fun update(item: CartItem)

    @Delete
    suspend fun delete(item: CartItem)

    @Query("DELETE FROM cart_items")
    suspend fun clearCart()
}