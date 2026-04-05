package com.projs.ecommerceshopping.model.response

data class ProductResponse(
    val status: Int,
    val message: String,
    val products: List<Product>
)