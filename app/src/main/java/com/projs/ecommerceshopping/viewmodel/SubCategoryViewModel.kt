package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.projs.ecommerceshopping.model.response.SubCategory
import com.projs.ecommerceshopping.repository.ISubCategoryRepository
import kotlinx.coroutines.launch

class SubCategoryViewModel(
    private val repository: ISubCategoryRepository
) : ViewModel() {

    val subCategories = MutableLiveData<List<SubCategory>>()
    val error = MutableLiveData<String>()

    fun fetchSubCategories(categoryId: String) {
        viewModelScope.launch {
            try {
                val response = repository.getSubCategories(categoryId)
                if (response.status == 0) {
                    subCategories.value = response.subcategories
                } else {
                    error.value = response.message
                }
            } catch (e: Exception) {
                error.value = e.message
            }
        }
    }
}

class SubCategoryViewModelFactory(
    private val repository: ISubCategoryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SubCategoryViewModel(repository) as T
    }
}