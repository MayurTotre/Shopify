package com.example.shopify.repositoryImpl

import com.example.shopify.model.response.DisplayProductResponse
import com.example.shopify.repository.GetProductDetailsRepository
import com.example.shopify.services.remote.ProductDetailsApiService
import javax.inject.Inject

class ProductDetailsRepositoryImpl @Inject constructor(
    private val apiService: ProductDetailsApiService
) : GetProductDetailsRepository {
    override suspend fun getProductsDetails(id: Int): Result<DisplayProductResponse> {
        return try {
            val response = apiService.getProductDetails(id)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Response body is null"))
            } else {
                Result.failure(Exception("API call failed with code: ${response.code()}======${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}