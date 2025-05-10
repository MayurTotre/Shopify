package com.example.shopify.viewmodel

import android.location.Address
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.model.response.AddressResponse
import com.example.shopify.repository.AddressDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddressViewModel @Inject constructor(
    private val repository: AddressDetailsRepository
): ViewModel() {

    fun addAddress(address: AddressResponse){
        viewModelScope.launch {
            repository.addAddress(address)
        }
    }

    fun removeAddress(id:Int){
        viewModelScope.launch {
            repository.removeAddress(id)
        }
    }

    private val _addressList = MutableLiveData<List<AddressResponse>>()
    val addressList: LiveData<List<AddressResponse>> = _addressList

    fun getAllAddressess(){
        viewModelScope.launch {
            val addressList = repository.getAllAddressess()
            _addressList.postValue(addressList)
        }
    }
}