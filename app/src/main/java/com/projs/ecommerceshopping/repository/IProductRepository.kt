package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.model.response.ProductResponse

interface IProductRepository {
    suspend fun getProducts(subCategoryId: String): ProductResponse
}