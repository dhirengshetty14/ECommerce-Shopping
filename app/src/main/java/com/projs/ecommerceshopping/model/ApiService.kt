package com.projs.ecommerceshopping.model

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("User/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse

    @POST("User/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse
}