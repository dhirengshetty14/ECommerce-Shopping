package com.projs.ecommerceshopping.model.response

data class Product(
    val product_id: String,
    val product_name: String,
    val description: String,
    val price: String,
    val product_image_url: String
)