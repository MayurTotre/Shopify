package com.example.shopify.model.request

data class LoginUserRequest(
    val email: String,
    val password: String
)