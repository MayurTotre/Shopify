package com.example.shopify.services.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shopify.model.response.AddressResponse
import com.example.shopify.model.response.AddtoCartProductResponse
import com.example.shopify.model.response.ProductsResponseItem
import com.example.shopify.services.typeconverters.Converter

@Database(entities = [ProductsResponseItem::class, AddtoCartProductResponse::class, AddressResponse::class], version = 5)
@TypeConverters(Converter::class)
abstract class ProductDB: RoomDatabase() {
    abstract fun addtoWishListDao(): WishListDao
    abstract fun addToCartDao(): AddToCartDao
    abstract fun addressDao():AddressDao
}

