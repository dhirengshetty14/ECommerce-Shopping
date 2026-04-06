package com.projs.ecommerceshopping.repository

import androidx.lifecycle.LiveData
import com.projs.ecommerceshopping.model.local.AddressDao
import com.projs.ecommerceshopping.model.local.AddressEntity
import com.projs.ecommerceshopping.remote.ApiClient
import com.projs.ecommerceshopping.model.response.AddAddressRequest

class AddressRepository(
    private val dao: AddressDao
) : IAddressRepository {

    private val api = ApiClient.apiService

    override suspend fun insert(address: AddressEntity) {
        dao.insert(address)
    }

    override fun getAll(): LiveData<List<AddressEntity>> {
        return dao.getAllAddresses()
    }

    override suspend fun fetchAddresses(userId: String) {
        try {
            val response = api.getAddresses(userId)

            if (response.status == 0) {

                dao.clearAll()

                response.addresses.forEach {
                    dao.insert(
                        AddressEntity(
                            title = it.title,
                            address = it.address
                        )
                    )
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun addAddressToServer(
        userId: String,
        title: String,
        address: String
    ) {
        try {
            api.addAddress(
                AddAddressRequest(userId, title, address)
            )

            dao.insert(AddressEntity(title = title, address = address))

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}