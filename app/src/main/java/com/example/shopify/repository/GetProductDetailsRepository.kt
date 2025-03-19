package com.example.shopify.repository

import com.example.shopify.model.response.CategoriesResponse
import com.example.shopify.model.response.DisplayProductResponse

interface GetProductDetailsRepository {
    suspend fun getProductsDetails(id: Int): Result<DisplayProductResponse>
}