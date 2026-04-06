package com.projs.ecommerceshopping.model.response

data class ProductSearchResponse(
    val status: Int,
    val message: String,
    val product: Product?
)
