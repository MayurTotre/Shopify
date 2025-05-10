package com.example.shopify.interfaces

import com.example.shopify.model.response.ProductsResponseItem

interface OnClickDoAction {
    fun onCliCkDoAction(product: ProductsResponseItem)
}