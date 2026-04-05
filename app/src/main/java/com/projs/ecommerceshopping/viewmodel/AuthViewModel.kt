package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.projs.ecommerceshopping.network.ApiResponse
import com.projs.ecommerceshopping.repository.IAuthRepository
import com.projs.ecommerceshopping.model.response.LoginRequest
import com.projs.ecommerceshopping.model.response.LoginResponse
import com.projs.ecommerceshopping.model.response.RegisterRequest
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: IAuthRepository
) : ViewModel() {

    val loginResult = MutableLiveData<LoginResponse>()
    val registerResult = MutableLiveData<ApiResponse>()
    val isLoading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun login(email: String, password: String) {

        if (email.isEmpty() || password.isEmpty()) {
            error.value = "All fields are required"
            return
        }

        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = repository.login(LoginRequest(email, password))
                loginResult.value = response
            } catch (e: Exception) {
                error.value = "Something went wrong"
            }
            isLoading.value = false
        }
    }

    fun register(name: String, mobile: String, email: String, password: String) {

        if (name.isEmpty() || mobile.isEmpty() || email.isEmpty() || password.isEmpty()) {
            error.value = "All fields are required"
            return
        }

        viewModelScope.launch {
            isLoading.value = true
            try {
                val response = repository.register(
                    RegisterRequest(name, mobile, email, password)
                )
                registerResult.value = response
            } catch (e: Exception) {
                error.value = "Something went wrong"
            }
            isLoading.value = false
        }
    }
}
class AuthViewModelFactory(
    private val repository: IAuthRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}