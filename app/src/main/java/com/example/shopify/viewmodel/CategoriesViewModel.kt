package com.example.shopify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.model.response.CategoriesResponse
import com.example.shopify.model.response.ProductsResponse
import com.example.shopify.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val repository: CategoryRepository
): ViewModel() {
    private val _categories = MutableLiveData<Result<CategoriesResponse>>()
    val categories: LiveData<Result<CategoriesResponse>> = _categories

    fun categories(){
        viewModelScope.launch {
            try {
                val result = repository.categoriesData()
                _categories.postValue(result)
            } catch (e: Exception) {
                _categories.postValue(Result.failure(e))
            }
        }
    }

    private val _products = MutableLiveData<Result<ProductsResponse>>()
    val products: LiveData<Result<ProductsResponse>> = _products

    fun products(id: Int){
        viewModelScope.launch {
            try {
                val result = repository.productsData(id)
                _products.postValue(result)
            } catch (e: Exception) {
                _products.postValue(Result.failure(e))
            }
        }
    }
}