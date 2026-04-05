package com.projs.ecommerceshopping.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items")
data class OrderItemEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val orderId: Int,

    val productName: String,

    val price: Double,

    val quantity: Int,

    val image: String
)