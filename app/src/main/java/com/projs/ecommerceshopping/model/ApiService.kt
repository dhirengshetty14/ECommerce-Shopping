package com.projs.ecommerceshopping.model

import com.projs.ecommerceshopping.model.response.CategoryResponse
import com.projs.ecommerceshopping.model.response.LoginRequest
import com.projs.ecommerceshopping.model.response.LoginResponse
import com.projs.ecommerceshopping.model.response.RegisterRequest
import com.projs.ecommerceshopping.model.response.SubCategoryResponse
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("User/register")
    suspend fun register(@Body request: RegisterRequest): ApiResponse

    @POST("User/auth")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET("Category")
    suspend fun getCategories(): CategoryResponse

    @GET("SubCategory")
    suspend fun getSubCategories(
        @Query("category_id") categoryId: String
    ): SubCategoryResponse
}