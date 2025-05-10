package com.example.shopify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.model.response.DisplayProductResponse
import com.example.shopify.repository.GetProductDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DisplayProductsViewModel @Inject constructor(
    private val productDetailsRepository: GetProductDetailsRepository
): ViewModel() {

    private val _productDetailsData = MutableLiveData<Result<DisplayProductResponse>>()
    val productDetailsData: LiveData<Result<DisplayProductResponse>> = _productDetailsData

    fun getProductDetails(id: Int){
        viewModelScope.launch {
            val response = productDetailsRepository.getProductsDetails(id)
            try {
                _productDetailsData.postValue(response)
            }catch (e: Exception){
                _productDetailsData.postValue(Result.failure(e))
            }
        }
    }
}