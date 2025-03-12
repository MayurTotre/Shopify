package com.example.shopify.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("product_details")
data class ProductsResponseItem(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
    val id: Int,
    val category: Category,
    val creationAt: String,
    val description: String,
    val images: List<String>,
    val price: Int,
    val slug: String,
    val title: String,
    val updatedAt: String
)