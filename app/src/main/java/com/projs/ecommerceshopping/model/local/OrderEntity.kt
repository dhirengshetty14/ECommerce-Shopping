package com.projs.ecommerceshopping.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey(autoGenerate = true)
    val orderId: Int = 0,
    val totalAmount: Double,
    val address: String,
    val paymentMethod: String
)
