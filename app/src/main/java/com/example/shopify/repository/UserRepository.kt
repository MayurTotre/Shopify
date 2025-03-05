package com.example.shopify.repository

import com.example.shopify.model.request.CreateUserRequest
import com.example.shopify.model.request.LoginUserRequest
import com.example.shopify.model.response.CreateUserResponse
import com.example.shopify.model.response.ImageToUrlResponse
import com.example.shopify.model.response.LoginUserResponse
import okhttp3.MultipartBody

interface UserRepository {
    suspend fun createUser(request: CreateUserRequest): Result<CreateUserResponse>

    suspend fun uploadImageToServer(file: MultipartBody.Part): Result<ImageToUrlResponse>

    suspend fun loginUser(loginUserRequest: LoginUserRequest): Result<LoginUserResponse>
}