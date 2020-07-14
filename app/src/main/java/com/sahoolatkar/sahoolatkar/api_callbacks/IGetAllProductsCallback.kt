package com.sahoolatkar.sahoolatkar.api_callbacks

import ProductApiModel

interface IGetAllProductsCallback {

    fun onGetAllProducts(products: MutableList<ProductApiModel>)

}