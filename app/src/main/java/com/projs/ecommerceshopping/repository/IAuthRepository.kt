package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.network.ApiResponse
import com.projs.ecommerceshopping.model.response.LoginRequest
import com.projs.ecommerceshopping.model.response.LoginResponse
import com.projs.ecommerceshopping.model.response.RegisterRequest

interface IAuthRepository {

    suspend fun login(request: LoginRequest): LoginResponse

    suspend fun register(request: RegisterRequest): ApiResponse
}