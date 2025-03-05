package com.example.shopify.repositoryImpl

import com.example.shopify.model.request.CreateUserRequest
import com.example.shopify.model.request.LoginUserRequest
import com.example.shopify.model.response.CreateUserResponse
import com.example.shopify.model.response.ImageToUrlResponse
import com.example.shopify.model.response.LoginUserResponse
import com.example.shopify.repository.UserRepository
import com.example.shopify.services.UserRegisterationApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: UserRegisterationApiService
) : UserRepository {

    override suspend fun createUser(request: CreateUserRequest): Result<CreateUserResponse> {
        return try {
            val response = apiService.createUser(request) // ✅ Calling suspend function correctly
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

    override suspend fun uploadImageToServer(file: MultipartBody.Part): Result<ImageToUrlResponse> {
        return try{
            val response = apiService.uploadImage(file)
            if(response.isSuccessful){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception("Filed to load image"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun loginUser(loginUserRequest: LoginUserRequest): Result<LoginUserResponse> {
        return try{
            val response = apiService.loginUser(loginUserRequest)
            if(response.isSuccessful){
                Result.success(response.body()!!)
            }else{
                Result.failure(Exception("Login failed!"))
            }
        }catch(e: Exception){
            Result.failure(e)
        }
    }

}

