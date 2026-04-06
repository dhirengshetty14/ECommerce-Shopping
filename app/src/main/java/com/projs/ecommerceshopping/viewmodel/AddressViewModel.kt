package com.projs.ecommerceshopping.viewmodel
import androidx.lifecycle.*

import kotlinx.coroutines.launch
import androidx.lifecycle.*
import com.projs.ecommerceshopping.model.local.AddressEntity
import com.projs.ecommerceshopping.repository.IAddressRepository
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddressViewModel(private val repo: IAddressRepository) : ViewModel() {

    val addresses: LiveData<List<AddressEntity>> = repo.getAll()

    fun insert(address: AddressEntity) {
        viewModelScope.launch {
            repo.insert(address)
        }
    }
}

class AddressViewModelFactory(private val repo: IAddressRepository)
    : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddressViewModel(repo) as T
    }
}