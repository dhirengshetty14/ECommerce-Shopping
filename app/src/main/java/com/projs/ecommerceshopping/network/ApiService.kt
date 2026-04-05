package com.projs.ecommerceshopping.network

import com.projs.ecommerceshopping.model.response.CategoryResponse
import com.projs.ecommerceshopping.model.response.LoginRequest
import com.projs.ecommerceshopping.model.response.LoginResponse
import com.projs.ecommerceshopping.model.response.ProductResponse
import com.projs.ecommerceshopping.model.response.RegisterRequest
import com.projs.ecommerceshopping.model.response.SubCategoryResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    @GET("SubCategory/products/{sub_category_id}")
    suspend fun getProducts(
        @Path("sub_category_id") subCategoryId: String
    ): ProductResponse
}