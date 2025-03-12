package com.example.shopify.di

import com.example.shopify.repository.CategoryRepository
import com.example.shopify.repository.WishListRepository
import com.example.shopify.repositoryImpl.CategoriesRepositoryImpl
import com.example.shopify.repositoryImpl.WishListRepositoryImpl
import com.example.shopify.services.local.ProductDB
import com.example.shopify.services.local.WishListDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WishListModule {
    @Provides
    @Singleton
    fun provideWishList(wishListRepositoryImpl: WishListRepositoryImpl): WishListRepository {
        return wishListRepositoryImpl
    }
}