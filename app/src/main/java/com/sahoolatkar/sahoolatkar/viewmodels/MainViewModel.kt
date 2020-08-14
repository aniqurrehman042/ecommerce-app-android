package com.sahoolatkar.sahoolatkar.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sahoolatkar.sahoolatkar.api_models.order.Order
import com.sahoolatkar.sahoolatkar.api_models.product.Product
import com.sahoolatkar.sahoolatkar.api_utils.SahoolatKarApiUtils
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import com.sahoolatkar.sahoolatkar.models.CartProduct
import com.sahoolatkar.sahoolatkar.models.CurrentCustomer
import com.sahoolatkar.sahoolatkar.utils.SharedPrefsUtils
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val mobiles: LiveData<List<Product>> by lazy<LiveData<List<Product>>>{

        MutableLiveData<List<Product>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProducts(
                    GlobalVariables.CATEGORY_MOBILES_ID,
                    pageNo = 1,
                    pageSize = 10
                )
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    val airConditioners: LiveData<List<Product>> by lazy<LiveData<List<Product>>>{

        MutableLiveData<List<Product>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProducts(
                    GlobalVariables.CATEGORY_INVERTER_AC_ID,
                    pageNo = 1,
                    pageSize = 10
                )
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    val featuredProducts: LiveData<List<Product>> by lazy<LiveData<List<Product>>> {
        MutableLiveData<List<Product>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProducts(
                    featured = true,
                    pageNo = 1,
                    pageSize = 10
                )
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    val offers: LiveData<List<Product>> by lazy<LiveData<List<Product>>> {
        MutableLiveData<List<Product>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getProducts(
                    GlobalVariables.CATEGORY_OFFERS,
                    pageNo = 1
                )
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    val orders: LiveData<List<Order>> by lazy<LiveData<List<Order>>> {
        MutableLiveData<List<Order>>().also {
            viewModelScope.launch {
                val response = SahoolatKarApiUtils.getOrders(CurrentCustomer.cusId)
                if (response.isSuccessful) {
                    it.value = response.body()
                }
            }
        }
    }

    val wishListProducts: MutableList<Product> = ArrayList()

    val cartProducts: MutableList<CartProduct> = ArrayList()

}