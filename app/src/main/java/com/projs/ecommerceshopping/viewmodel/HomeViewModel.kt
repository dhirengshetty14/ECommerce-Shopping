package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.projs.ecommerceshopping.model.response.Category
import com.projs.ecommerceshopping.repository.ICategoryRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: ICategoryRepository): ViewModel() {

    val categories= MutableLiveData<List<Category>>()
    val error= MutableLiveData<String>()
    val isLoading= MutableLiveData<Boolean>()

    fun fetchCategories(){
        viewModelScope.launch {
            isLoading.value=true

            try {
                val response = repository.getCategories()
                if (response.status == 0) {
                    categories.value = response.categories
                } else {
                    error.value = response.message
                }
            } catch (e: Exception) {
                error.value = e.message
            }
            isLoading.value = false
        }
    }
}

class HomeViewModelFactory(
    private val repository: ICategoryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(repository) as T
    }
}