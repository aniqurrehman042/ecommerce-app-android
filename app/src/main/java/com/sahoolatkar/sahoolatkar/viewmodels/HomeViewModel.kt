package com.sahoolatkar.sahoolatkar.viewmodels

import ProductApiModel
import androidx.lifecycle.*
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val mobiles: MutableLiveData<List<ProductApiModel>> by lazy {
        MutableLiveData<List<ProductApiModel>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProductsByCategoryWithCo(GlobalVariables.CATEGORY_MOBILES_ID, 1)
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    fun getMobiles(): LiveData<List<ProductApiModel>> {
        return mobiles
    }
}