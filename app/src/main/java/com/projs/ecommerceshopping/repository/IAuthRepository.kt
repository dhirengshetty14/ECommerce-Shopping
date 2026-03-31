package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.model.ApiResponse
import com.projs.ecommerceshopping.model.LoginRequest
import com.projs.ecommerceshopping.model.LoginResponse
import com.projs.ecommerceshopping.model.RegisterRequest

interface IAuthRepository {

    suspend fun login(request: LoginRequest): LoginResponse

    suspend fun register(request: RegisterRequest): ApiResponse
}