package com.example.shopify.services.remote

import com.example.shopify.model.response.DisplayProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailsApiService {
    @GET("products/{id}")
    suspend fun getProductDetails(
        @Path("id") id: Int
    ): Response<DisplayProductResponse>

}