package com.example.shopify.model.response

data class LoginUserResponse(
    val access_token: String,
    val refresh_token: String
)