package com.example.shoppingtestingapp.domain.model.registration


import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("env")
    val env: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)