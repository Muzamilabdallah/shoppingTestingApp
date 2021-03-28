package com.example.shoppingtestingapp.domain


import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("data")
    val `data`: List<Product>,
    @SerializedName("env")
    val env: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)