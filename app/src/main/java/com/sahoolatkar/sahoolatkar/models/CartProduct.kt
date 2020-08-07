package com.sahoolatkar.sahoolatkar.models

import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import com.sahoolatkar.sahoolatkar.globals.GlobalVariables
import java.io.Serializable

class CartProduct(var product: ProductApiModel, var quantity: Int,
                  var installments: Int = GlobalVariables.INSTALLMENTS_1) : Serializable {
}