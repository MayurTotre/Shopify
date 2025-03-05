package com.example.shopify.di

import com.example.shopify.repository.UserRepository
import com.example.shopify.repositoryImpl.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CreateUserModule {
    @Provides
    @Singleton
    fun provideUser(user: UserRepositoryImpl): UserRepository {
        return user
    }
}