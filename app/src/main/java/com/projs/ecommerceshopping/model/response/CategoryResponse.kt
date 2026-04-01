package com.projs.ecommerceshopping.model.response

data class CategoryResponse(
    val status: Int,
    val message: String,
    val categories: List<Category>
)