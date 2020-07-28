package com.sahoolatkar.sahoolatkar.api_callbacks

import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel

interface IGetAllProductsCallback {

    fun onGetProducts(products: MutableList<ProductApiModel>)

}