package com.example.shopify.model.response

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "address")
data class AddressResponse(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    val city: String,
    val state: String,
    val zipCode: Int,
    val country: String
)