package com.sahoolatkar.sahoolatkar.api_callbacks

import com.sahoolatkar.sahoolatkar.api_models.product.Product

interface IGetAllProductsCallback {

    fun onGetProducts(products: MutableList<Product>)

}