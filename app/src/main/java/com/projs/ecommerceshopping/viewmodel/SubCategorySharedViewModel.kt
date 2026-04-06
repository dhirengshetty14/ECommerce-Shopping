package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubCategorySharedViewModel : ViewModel() {

    val subCategoryId = MutableLiveData<String>()
}