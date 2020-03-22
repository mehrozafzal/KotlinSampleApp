package com.example.mobiquitytask.data.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductsItem(

    @field:SerializedName("salePrice")
    val salePrice: SalePrice? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("categoryId")
    val categoryId: String? = null,

    @field:SerializedName("url")
    val url: String? = null
) : Parcelable