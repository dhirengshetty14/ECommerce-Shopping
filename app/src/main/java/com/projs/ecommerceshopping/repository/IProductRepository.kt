package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.model.response.ProductDetailsResponse
import com.projs.ecommerceshopping.model.response.ProductResponse
import com.projs.ecommerceshopping.model.response.ProductSearchResponse

interface IProductRepository {
    suspend fun getProducts(subCategoryId: String): ProductResponse
    suspend fun searchProduct(query: String): ProductSearchResponse

    suspend fun getProductDetails(productId: String): ProductDetailsResponse
}