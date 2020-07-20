package com.sahoolatkar.sahoolatkar.viewmodels

import ProductApiModel
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import kotlinx.coroutines.Dispatchers

class ProductViewModel(categoryId: String) : ViewModel() {
    private val products: MutableLiveData<List<ProductApiModel>> =
        liveData<List<ProductApiModel>>(Dispatchers.IO) {
            SahoolatKarApiUtils.getProductsByCategoryWithCo(categoryId)
        } as MutableLiveData<List<ProductApiModel>>

    fun getProducts(): LiveData<List<ProductApiModel>> {
        return products
    }
}