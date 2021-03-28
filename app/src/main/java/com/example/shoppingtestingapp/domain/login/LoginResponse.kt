package com.example.shoppingtestingapp.domain.login


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("env")
    val env: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)