package com.projs.ecommerceshopping.repository

import androidx.lifecycle.LiveData
import com.projs.ecommerceshopping.model.local.AddressDao
import com.projs.ecommerceshopping.model.local.AddressEntity

class AddressRepository(private val dao: AddressDao) : IAddressRepository {

    override suspend fun insert(address: AddressEntity) {
        dao.insert(address)
    }

    override fun getAll(): LiveData<List<AddressEntity>> {
        return dao.getAllAddresses()
    }
}