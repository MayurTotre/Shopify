package com.example.shopify.repositoryImpl

import com.example.shopify.model.response.AddtoCartProductResponse
import com.example.shopify.repository.AddToCartRepository
import com.example.shopify.services.local.AddToCartDao
import javax.inject.Inject

class AddToCartRepositoryImpl @Inject constructor(
    private val addToCartDao: AddToCartDao
): AddToCartRepository {
    override suspend fun addItemInCart(id: AddtoCartProductResponse) {
        addToCartDao.addItemToCart(id)
    }

    override suspend fun getProductFromAddToCart(): List<AddtoCartProductResponse> {
        return addToCartDao.getAllItemsFromCart()
    }

    override suspend fun deleteProductFromCart(id: Int) {
        addToCartDao.deleteProductFromCart(id)
    }

    override suspend fun deleteAllProductsFromCart() {
        addToCartDao.deleteAllItems()
    }

    override suspend fun isItemAvailableInCart(id: Int): Boolean {
        return addToCartDao.isAvailableInCart(id) != null
    }
}