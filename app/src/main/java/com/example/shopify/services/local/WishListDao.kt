package com.example.shopify.services.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopify.model.response.ProductsResponse
import com.example.shopify.model.response.ProductsResponseItem

@Dao
interface WishListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToWishList(item: ProductsResponseItem)

    @Query("Select * from product_details")
    suspend fun getProduct(): List<ProductsResponseItem>

    @Query("Delete from product_details where id = :id")
    suspend fun deleteProduct(id:Int)

    @Query("Select * from product_details where id = :id")
    suspend fun isProductInWishList(id: Int): ProductsResponseItem?


}