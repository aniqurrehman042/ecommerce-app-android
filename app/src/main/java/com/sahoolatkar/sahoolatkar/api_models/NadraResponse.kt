package com.sahoolatkar.sahoolatkar.api_models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class NadraResponse(
    @Expose @SerializedName("name") var name: String,
    @Expose @SerializedName("sex") var sex: String,
    @Expose @SerializedName("father_husband_name") var fatherHusbandName: String,
    @Expose @SerializedName("identification_mark") var identificationMark: String,
    @Expose @SerializedName("dob") var dob: Date,
    @Expose @SerializedName("family_no") var familyNo: String,
    @Expose @SerializedName("address") var address: String,
    @Expose @SerializedName("confirm_address") var confirmAddress: String,
    @Expose @SerializedName("expiry") var expiry: Date
)