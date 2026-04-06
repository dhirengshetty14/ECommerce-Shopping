package com.projs.ecommerceshopping.model.response

data class AddressResponse(
    val status: Int,
    val message: String,
    val addresses: List<AddressDto>
)

data class AddressDto(
    val title: String,
    val address: String
)