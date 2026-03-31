package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.model.ApiResponse
import com.projs.ecommerceshopping.model.LoginRequest
import com.projs.ecommerceshopping.model.LoginResponse
import com.projs.ecommerceshopping.model.RegisterRequest

class AuthRepository : IAuthRepository {

    override suspend fun login(request: LoginRequest): LoginResponse {
        return RetrofitInstance.api.login(request)
    }

    override suspend fun register(request: RegisterRequest): ApiResponse {
        return RetrofitInstance.api.register(request)
    }
}