package com.projs.ecommerceshopping.model

import com.projs.ecommerceshopping.model.response.CategoryResponse
import com.projs.ecommerceshopping.model.response.LoginRequest
import com.projs.ecommerceshopping.model.response.LoginResponse
import com.projs.ecommerceshopping.model.response.RegisterRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("User/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse

    @POST("User/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("Category")
    suspend fun getCategories(): CategoryResponse
}