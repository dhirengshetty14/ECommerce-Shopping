package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.model.response.ProductResponse
import com.projs.ecommerceshopping.remote.ApiClient

class ProductRepository : IProductRepository {

    override suspend fun getProducts(subCategoryId: String): ProductResponse {
        return ApiClient.apiService.getProducts(subCategoryId)
    }
}