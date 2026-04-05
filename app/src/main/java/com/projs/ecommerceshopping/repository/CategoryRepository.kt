package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.network.ApiClient
import com.projs.ecommerceshopping.model.response.CategoryResponse

class CategoryRepository : ICategoryRepository {

    override suspend fun getCategories(): CategoryResponse {
        return ApiClient.apiService.getCategories()
    }
}