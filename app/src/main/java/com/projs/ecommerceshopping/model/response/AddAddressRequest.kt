package com.projs.ecommerceshopping.model.response

data class AddAddressRequest(
    val user_id: String,
    val title: String,
    val address: String
)