package com.example.shopify.di

import com.example.shopify.repository.AddressDetailsRepository
import com.example.shopify.repository.WishListRepository
import com.example.shopify.repositoryImpl.AddressDetailsRepositoryImpl
import com.example.shopify.repositoryImpl.WishListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AddressDetails {
    @Provides
    @Singleton
    fun provideAddressDetails(addressDetailsRepositoryImpl: AddressDetailsRepositoryImpl): AddressDetailsRepository {
        return addressDetailsRepositoryImpl
    }
}