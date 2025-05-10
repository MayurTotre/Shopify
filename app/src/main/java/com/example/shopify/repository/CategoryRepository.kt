package com.example.shopify.repository

import com.example.shopify.model.response.CategoriesResponse
import com.example.shopify.model.response.ProductsResponse

interface CategoryRepository {
    suspend fun categoriesData(): Result<CategoriesResponse>

    suspend fun productsData(id: Int): Result<ProductsResponse>

}