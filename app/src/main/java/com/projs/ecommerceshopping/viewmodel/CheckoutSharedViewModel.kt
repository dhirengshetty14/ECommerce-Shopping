package com.projs.ecommerceshopping.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CheckoutSharedViewModel : ViewModel() {

    val selectedAddress = MutableLiveData<String>()
    val selectedPayment = MutableLiveData<String>()
}