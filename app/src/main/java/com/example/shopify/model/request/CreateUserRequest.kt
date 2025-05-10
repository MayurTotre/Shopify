package com.example.shopify.model.request

data class CreateUserRequest(
    val avatar: String,
    val email: String,
    val name: String,
    val password: String
)