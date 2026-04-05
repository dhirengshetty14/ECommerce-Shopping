package com.projs.ecommerceshopping.repository

import com.projs.ecommerceshopping.model.response.SubCategoryResponse

interface ISubCategoryRepository {
    suspend fun getSubCategories(categoryId: String): SubCategoryResponse
}