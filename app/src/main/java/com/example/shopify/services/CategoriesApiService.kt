package com.example.shopify.services

import com.example.shopify.model.response.CategoriesResponse
import retrofit2.Response
import retrofit2.http.GET

interface CategoriesApiService {
    @GET("categories")
    suspend fun getProductCategories(): Response<CategoriesResponse>
}