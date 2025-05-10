package com.example.shopify.repository

import com.example.shopify.model.response.AddressResponse

interface AddressDetailsRepository {

    suspend fun addAddress(address: AddressResponse)

    suspend fun removeAddress(id: Int)

    suspend fun getAllAddressess(): List<AddressResponse>
}