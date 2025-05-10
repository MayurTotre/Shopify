package com.example.shopify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.model.response.ProductsResponseItem
import com.example.shopify.repository.WishListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(
    private val wishListRepository: WishListRepository
) : ViewModel() {

    fun addToWishList(item: ProductsResponseItem) {
        viewModelScope.launch {
            wishListRepository.addToWishList(item)
        }
    }

    fun deleteProduct(id: Int) {
        viewModelScope.launch {
            wishListRepository.deleteProduct(id)
        }
    }

    fun isProductInWishList(id: Int) {
        viewModelScope.launch {
            wishListRepository.isProductInWishList(id)
        }
    }


    private val _productsResponseItem = MutableLiveData<List<ProductsResponseItem>>()
    val productsResponseItem: LiveData<List<ProductsResponseItem>> = _productsResponseItem

    fun getAllProducts() {
        viewModelScope.launch {
            _productsResponseItem.postValue(wishListRepository.getProduct())
        }
    }

}