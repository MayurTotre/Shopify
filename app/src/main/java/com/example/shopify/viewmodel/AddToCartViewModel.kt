package com.example.shopify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.model.response.AddtoCartProductResponse
import com.example.shopify.repository.AddToCartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddToCartViewModel @Inject constructor(
    private val repository: AddToCartRepository
) : ViewModel() {

    fun addItemToCart(item: AddtoCartProductResponse) {
        viewModelScope.launch {
            repository.addItemInCart(item)

        }
    }

    fun deleteProductFromCart(id: Int){
        viewModelScope.launch {
            repository.deleteProductFromCart(id)
        }
    }

    fun deleteAllProductsFromCart(){
        viewModelScope.launch {
            repository.deleteAllProductsFromCart()
        }
    }

    private val _addToCartData = MutableLiveData<List<AddtoCartProductResponse>>()
    val addToCartData: LiveData<List<AddtoCartProductResponse>> = _addToCartData

    fun getAllItemsFromCart(){
        viewModelScope.launch {
            val response = repository.getProductFromAddToCart()
            _addToCartData.postValue(response)
        }
    }

    suspend fun isItemAvailableInCart(id: Int): Boolean{
        return repository.isItemAvailableInCart(id)
    }
}