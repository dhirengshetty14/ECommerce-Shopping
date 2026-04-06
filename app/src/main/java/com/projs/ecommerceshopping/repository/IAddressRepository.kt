package com.projs.ecommerceshopping.repository

import androidx.lifecycle.LiveData
import com.projs.ecommerceshopping.model.local.AddressEntity

interface IAddressRepository {
    suspend fun insert(address: AddressEntity)
    fun getAll(): LiveData<List<AddressEntity>>
}