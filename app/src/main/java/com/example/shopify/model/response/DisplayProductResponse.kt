package com.example.shopify.model.response

data class DisplayProductResponse(
    val category: Category,
    val creationAt: String,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Long,
    val slug: String,
    val title: String,
    val updatedAt: String
)