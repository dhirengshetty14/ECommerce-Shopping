package com.projs.ecommerceshopping.model.response

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email_id")
    val email_id: String,
    @SerializedName("password")
    val password: String
)