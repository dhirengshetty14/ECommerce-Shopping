package com.projs.ecommerceshopping.model.response

data class ProductDetails(
    val product_id: String,
    val product_name: String,
    val description: String,
    val category_id: String,
    val sub_category_id: String,
    val price: String,
    val average_rating: String,
    val product_image_url: String,
    val is_active: String,

    val images: List<ProductImage>,
    val specifications: List<Specification>,
    val reviews: List<Review>
)