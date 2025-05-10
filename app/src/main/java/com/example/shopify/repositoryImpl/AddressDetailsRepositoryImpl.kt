package com.example.shopify.repositoryImpl

import com.example.shopify.model.response.AddressResponse
import com.example.shopify.repository.AddressDetailsRepository
import com.example.shopify.services.local.AddressDao
import javax.inject.Inject

class AddressDetailsRepositoryImpl @Inject constructor(
    private val addressDao: AddressDao
):AddressDetailsRepository {
    override suspend fun addAddress(address: AddressResponse) {
        addressDao.insertAddress(address)
    }

    override suspend fun removeAddress(id: Int) {
        addressDao.deleteAddress(id)
    }

    override suspend fun getAllAddressess(): List<AddressResponse> {
        return addressDao.getAddress()
    }

}