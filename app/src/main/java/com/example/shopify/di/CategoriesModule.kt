package com.example.shopify.di

import com.example.shopify.repository.CategoryRepository
import com.example.shopify.repository.UserRepository
import com.example.shopify.repositoryImpl.CategoriesRepositoryImpl
import com.example.shopify.repositoryImpl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class CategoriesModule {
    @Provides
    @Singleton
    fun provideUser(categoryRepositoryImpl: CategoriesRepositoryImpl): CategoryRepository {
        return categoryRepositoryImpl
    }
}
