package com.example.shopify.di

import com.example.shopify.repository.AddToCartRepository
import com.example.shopify.repository.WishListRepository
import com.example.shopify.repositoryImpl.AddToCartRepositoryImpl
import com.example.shopify.repositoryImpl.WishListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AddToCartModule {
    @Provides
    @Singleton
    fun provideAddToCartList(addToCartRepositoryImpl: AddToCartRepositoryImpl): AddToCartRepository {
        return addToCartRepositoryImpl
    }
}