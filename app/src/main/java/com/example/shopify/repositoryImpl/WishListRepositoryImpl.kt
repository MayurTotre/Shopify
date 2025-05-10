package com.example.shopify.repositoryImpl

import com.example.shopify.model.response.ProductsResponseItem
import com.example.shopify.repository.WishListRepository
import com.example.shopify.services.local.WishListDao
import javax.inject.Inject

class WishListRepositoryImpl @Inject constructor(
    private val wishListDao: WishListDao
): WishListRepository {
    override suspend fun addToWishList(item: ProductsResponseItem) {
        wishListDao.addToWishList(item)
    }

    override suspend fun getProduct(): List<ProductsResponseItem> {
        return wishListDao.getProduct()
    }

    override suspend fun deleteProduct(id: Int) {
        wishListDao.deleteProduct(id)
    }

    override suspend fun isProductInWishList(id: Int): Boolean {
        return wishListDao.isProductInWishList(id) != null
    }
}