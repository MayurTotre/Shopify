package com.example.shopify.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopify.model.response.ProductsResponse
import com.example.shopify.model.response.ProductsResponseItem
import com.example.shopify.services.local.ProductDB
import com.example.shopify.services.local.WishListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun getInstance(@ApplicationContext context: Context): ProductDB {
        return Room.databaseBuilder(
            context.applicationContext,
            ProductDB::class.java,
            "products_database_new"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideWishListDao(db: ProductDB): WishListDao {
        return db.addtoWishListDao()
    }

}
