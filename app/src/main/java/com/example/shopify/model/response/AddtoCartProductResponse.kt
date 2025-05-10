package com.example.shopify.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("add_to_cart")
data class AddtoCartProductResponse(
    @PrimaryKey(autoGenerate = true)
    val productId: Int = 0,
    val id: Int,
    val category: Category,
    val creationAt: String,
    val description: String,
    val images: List<String>,
    val price: Long,
    val slug: String,
    val title: String,
    val updatedAt: String,
    var quantity: Int
)