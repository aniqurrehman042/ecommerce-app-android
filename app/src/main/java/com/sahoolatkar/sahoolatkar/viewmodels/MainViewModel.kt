package com.sahoolatkar.sahoolatkar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    v val mobiles: LiveData<List<ProductApiModel>> by lazy<LiveData<List<ProductApiModel>>> {
        MutableLiveData<List<ProductApiModel>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProductsWithCo(
                    GlobalVariables.CATEGORY_MOBILES_ID,
                    null,
                    1
                )
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    val featuredProducts: LiveData<List<ProductApiModel>> by lazy<LiveData<List<ProductApiModel>>> {
        MutableLiveData<List<ProductApiModel>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProductsWithCo(
                    null,
                    true,
                    1
                )
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    val offers: LiveData<List<ProductApiModel>> by lazy<LiveData<List<ProductApiModel>>> {
        MutableLiveData<List<ProductApiModel>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProductsWithCo(
                    GlobalVariables.CATEGORY_OFFERS,
                    null,
                    1
                )
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

}