package com.example.mobiquitytask.data.model.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class SalePrice(

    @field:SerializedName("amount")
    val amount: String? = null,

    @field:SerializedName("currency")
    val currency: String? = null
) : Parcelable