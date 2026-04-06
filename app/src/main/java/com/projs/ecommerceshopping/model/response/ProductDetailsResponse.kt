package com.projs.ecommerceshopping.model.response

data class ProductDetailsResponse(
    val status: Int,
    val message: String,
    val product: ProductDetails
)