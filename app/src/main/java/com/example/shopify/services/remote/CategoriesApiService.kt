package com.example.shopify.services.remote

import com.example.shopify.model.response.CategoriesResponse
import com.example.shopify.model.response.ProductsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriesApiService {
    @GET("categories")
    suspend fun getProductCategories(): Response<CategoriesResponse>

    @GET("categories/{id}/products")
    suspend fun getProducts(
        @Path("id") id: Int
    ): Response<ProductsResponse>
}