package com.projs.ecommerceshopping.model.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AddressDao {

    @Insert
    suspend fun insert(address: AddressEntity)

    @Query("SELECT * FROM addresses")
    fun getAllAddresses(): LiveData<List<AddressEntity>>

    @Delete
    suspend fun delete(address: AddressEntity)
    @Query("DELETE FROM addresses")
    suspend fun clearAll()
}