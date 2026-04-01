package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.model.response.CategoryResponse

interface ICategoryRepository {
    suspend fun getCategories(): CategoryResponse
}