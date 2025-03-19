package com.example.shopify.interfaces

import com.example.shopify.model.response.CategoriesResponse
import com.example.shopify.model.response.CategoriesResponseItem

interface OnClickGetDetails {
    fun getProductById(category: CategoriesResponseItem)
}