package com.projs.ecommerceshopping.model.response

data class Review(
    val user_id: String,
    val full_name: String,
    val review_id: String,
    val review_title: String,
    val review: String,
    val rating: String,
    val review_date: String
)