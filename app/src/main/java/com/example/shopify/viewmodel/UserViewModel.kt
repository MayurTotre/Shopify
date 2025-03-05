package com.example.shopify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shopify.model.request.CreateUserRequest
import com.example.shopify.model.request.LoginUserRequest
import com.example.shopify.model.response.CreateUserResponse
import com.example.shopify.model.response.ImageToUrlResponse
import com.example.shopify.model.response.LoginUserResponse
import com.example.shopify.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    private val _createUser = MutableLiveData<Result<CreateUserResponse>>()
    val createUser: LiveData<Result<CreateUserResponse>> = _createUser

    fun registerUser(request: CreateUserRequest){
        viewModelScope.launch {
            try {
                val result = userRepository.createUser(request)
                _createUser.postValue(result)
            } catch (e: Exception) {
                _createUser.postValue(Result.failure(e))
            }
        }
    }

    private val _uploadImage = MutableLiveData<Result<ImageToUrlResponse>>()
    val uploadImage: LiveData<Result<ImageToUrlResponse>> = _uploadImage

    fun uploadImageToServer(file: File){
        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        viewModelScope.launch {
            try {
                val result = userRepository.uploadImageToServer(body)
                _uploadImage.postValue(result)
            }catch (e: Exception){
                _uploadImage.postValue(Result.failure(e))
            }
        }
    }

    private val _loginState = MutableLiveData<Result<LoginUserResponse>>()
    val loginState: LiveData<Result<LoginUserResponse>> = _loginState

    fun loginUser(loginUserRequest: LoginUserRequest){
        viewModelScope.launch {
            val result = userRepository.loginUser(loginUserRequest)
            try{
                _loginState.postValue(result)
            }catch (e: Exception){
                _loginState.postValue(Result.failure(e))
            }
        }
    }
}