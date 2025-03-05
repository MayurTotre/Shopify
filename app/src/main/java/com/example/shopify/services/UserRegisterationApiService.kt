package com.example.shopify.services

import com.example.shopify.model.request.CreateUserRequest
import com.example.shopify.model.request.LoginUserRequest
import com.example.shopify.model.response.CreateUserResponse
import com.example.shopify.model.response.ImageToUrlResponse
import com.example.shopify.model.response.LoginUserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UserRegisterationApiService {
    @POST("users/")
    suspend fun createUser(@Body createUserRequest: CreateUserRequest): Response<CreateUserResponse>

    @Multipart
    @POST("files/upload")
    suspend fun uploadImage(@Part file: MultipartBody.Part): Response<ImageToUrlResponse>

    @POST("auth/login")
    suspend fun loginUser(@Body loginUserRequest: LoginUserRequest): Response<LoginUserResponse>

}