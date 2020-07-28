package com.sahoolatkar.sahoolatkar.viewmodels

import ProductApiModel
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val cartProducts: MutableList<ProductApiModel> = ArrayList()

}