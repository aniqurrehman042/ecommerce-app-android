package com.sahoolatkar.sahoolatkar.viewmodels

import androidx.lifecycle.ViewModel
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel

class MainViewModel : ViewModel() {

    val cartProducts: MutableList<ProductApiModel> = ArrayList()

}