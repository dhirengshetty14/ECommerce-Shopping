package com.projs.ecommerceshopping.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItem(

    @PrimaryKey
    val product_id: String,

    val product_name: String,
    val price: String,
    val image: String,
    var quantity: Int
)