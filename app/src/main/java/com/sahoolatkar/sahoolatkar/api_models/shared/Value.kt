package com.sahoolatkar.sahoolatkar.api_models.shared

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Value(
    @Expose @SerializedName("sharing") var sharing: Int,
    var value: String
)