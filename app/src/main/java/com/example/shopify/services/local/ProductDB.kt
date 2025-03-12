package com.example.shopify.services.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shopify.model.response.ProductsResponseItem
import com.example.shopify.services.typeconverters.Converter

@Database(entities = [ProductsResponseItem::class], version = 2)
@TypeConverters(Converter::class)
abstract class ProductDB: RoomDatabase() {
    abstract fun addtoWishListDao(): WishListDao
}
