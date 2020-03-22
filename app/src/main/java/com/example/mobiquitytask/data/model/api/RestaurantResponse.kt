package com.example.mobiquitytask.data.model.api

import com.example.mobiquitytask.data.model.api.ProductsItem
import com.google.gson.annotations.SerializedName

data class RestaurantResponse(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("products")
	val products: List<ProductsItem>? = null


)