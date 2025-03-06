package com.example.shopify.repositoryImpl

import androidx.lifecycle.findViewTreeLifecycleOwner
import com.example.shopify.model.response.CategoriesResponse
import com.example.shopify.repository.CategoryRepository
import com.example.shopify.services.CategoriesApiService
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val apiService: CategoriesApiService
): CategoryRepository {

    override suspend fun categoriesData(): Result<CategoriesResponse>{
        return try {
            val response = apiService.getProductCategories()
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Response body is null"))
            } else {
                Result.failure(Exception("API call failed with code: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}