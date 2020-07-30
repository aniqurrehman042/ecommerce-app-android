package com.sahoolatkar.sahoolatkar.models

import com.sahoolatkar.sahoolatkar.api_models.product.ProductApiModel
import java.io.Serializable

class CartProduct(var product: ProductApiModel, var quantity: Int) : Serializable {
}