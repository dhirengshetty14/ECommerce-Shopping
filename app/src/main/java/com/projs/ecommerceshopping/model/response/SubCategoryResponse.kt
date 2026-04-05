package com.projs.ecommerceshopping.model.response

data class SubCategoryResponse(
    val status: Int,
    val message: String,
    val subcategories: List<SubCategory>
)