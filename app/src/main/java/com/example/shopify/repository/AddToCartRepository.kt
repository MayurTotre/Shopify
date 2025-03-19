package com.example.shopify.repository

import com.example.shopify.model.response.AddtoCartProductResponse
import com.example.shopify.model.response.ProductsResponseItem

interface AddToCartRepository {
    suspend fun addItemInCart(id: AddtoCartProductResponse)

    suspend fun getProductFromAddToCart(): List<AddtoCartProductResponse>

    suspend fun deleteProductFromCart(id: Int)

    suspend fun deleteAllProductsFromCart()

    suspend fun isItemAvailableInCart(id: Int): Boolean
}