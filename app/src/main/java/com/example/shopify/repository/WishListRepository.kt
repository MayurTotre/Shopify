package com.example.shopify.repository

import com.example.shopify.model.response.ProductsResponseItem

interface WishListRepository {

    suspend fun addToWishList(item: ProductsResponseItem)

    suspend fun getProduct(): List<ProductsResponseItem>

    suspend fun deleteProduct(id:Int)

    suspend fun isProductInWishList(id: Int): Boolean
}