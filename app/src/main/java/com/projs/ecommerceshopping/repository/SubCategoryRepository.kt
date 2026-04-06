package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.remote.ApiClient
import com.projs.ecommerceshopping.model.response.SubCategoryResponse

class SubCategoryRepository : ISubCategoryRepository {

    override suspend fun getSubCategories(categoryId: String): SubCategoryResponse {
        return ApiClient.apiService.getSubCategories(categoryId)
    }
}