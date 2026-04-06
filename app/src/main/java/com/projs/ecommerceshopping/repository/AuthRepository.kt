package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.remote.ApiClient
import com.projs.ecommerceshopping.remote.ApiResponse
import com.projs.ecommerceshopping.model.response.LoginRequest
import com.projs.ecommerceshopping.model.response.LoginResponse
import com.projs.ecommerceshopping.model.response.RegisterRequest

class AuthRepository : IAuthRepository {

    override suspend fun login(request: LoginRequest): LoginResponse {
        return ApiClient.apiService.login(request)
    }

    override suspend fun register(request: RegisterRequest): ApiResponse {
        return ApiClient.apiService.register(request)
    }
}