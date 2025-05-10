package com.example.shopify.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shopify.model.response.AddressResponse

@Dao
interface AddressDao {
    @Insert
    suspend fun insertAddress(address: AddressResponse)

    @Query("delete from address where id=:id")
    suspend fun deleteAddress(id : Int)

    @Query("select * from address")
    suspend fun getAddress(): List<AddressResponse>
}