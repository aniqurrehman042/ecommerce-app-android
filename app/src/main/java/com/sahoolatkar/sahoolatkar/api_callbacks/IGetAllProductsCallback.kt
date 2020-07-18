package com.sahoolatkar.sahoolatkar.api_callbacks

import ProductApiModel

interface IGetAllProductsCallback {

    fun onGetProducts(products: MutableList<ProductApiModel>)

}