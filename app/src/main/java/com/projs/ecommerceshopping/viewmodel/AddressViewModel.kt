package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.*
import com.projs.ecommerceshopping.model.local.AddressEntity
import com.projs.ecommerceshopping.repository.IAddressRepository
import kotlinx.coroutines.launch

class AddressViewModel(private val repo: IAddressRepository) : ViewModel() {

    val addresses: LiveData<List<AddressEntity>> = repo.getAll()

    fun insert(address: AddressEntity) {
        viewModelScope.launch {
            repo.insert(address)
        }
    }

    fun fetchAddresses(userId: String) {
        viewModelScope.launch {
            repo.fetchAddresses(userId)
        }
    }

    fun addAddress(userId: String, title: String, address: String) {
        viewModelScope.launch {
            repo.addAddressToServer(userId, title, address)
        }
    }
}

class AddressViewModelFactory(private val repo: IAddressRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddressViewModel(repo) as T
    }
}