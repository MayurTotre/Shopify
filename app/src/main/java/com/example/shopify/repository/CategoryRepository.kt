package com.example.shopify.repository

import com.example.shopify.model.response.CategoriesResponse

interface CategoryRepository {
    suspend fun categoriesData(): Result<CategoriesResponse>
}