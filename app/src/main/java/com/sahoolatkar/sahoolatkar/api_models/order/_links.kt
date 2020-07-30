package com.sahoolatkar.sahoolatkar.api_models.order

import com.google.gson.annotations.SerializedName

data class _links (

	@SerializedName("self") val self : List<Self>,
	@SerializedName("collection") val collection : List<Collection>
)