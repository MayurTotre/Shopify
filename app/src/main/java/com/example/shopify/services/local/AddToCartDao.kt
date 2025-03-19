package com.example.shopify.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopify.model.response.AddtoCartProductResponse

@Dao
interface AddToCartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addItemToCart(item: AddtoCartProductResponse)

    @Query("Select * from add_to_cart")
    suspend fun getAllItemsFromCart(): List<AddtoCartProductResponse>

    @Query("Delete from add_to_cart where id = :id")
    suspend fun deleteProductFromCart(id:Int)

    @Query("Delete from add_to_cart")
    suspend fun deleteAllItems()

    @Query("Select * from add_to_cart where id = :id")
    suspend fun isAvailableInCart(id: Int): AddtoCartProductResponse

}