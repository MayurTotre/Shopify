package com.example.shopify.di

import com.example.shopify.repository.GetProductDetailsRepository
import com.example.shopify.repositoryImpl.ProductDetailsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ProductDetailsModule {

    @Provides
    @Singleton
    fun provideProductDetails(productDetailsRepositoryImpl: ProductDetailsRepositoryImpl): GetProductDetailsRepository {
        return productDetailsRepositoryImpl
    }

}