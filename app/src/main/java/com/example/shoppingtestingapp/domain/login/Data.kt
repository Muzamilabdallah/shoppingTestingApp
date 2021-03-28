package com.example.shoppingtestingapp.domain.login


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("branch")
    val branch: List<Int>,
    @SerializedName("role")
    val role: String,
    @SerializedName("role_id")
    val roleId: Int,
    @SerializedName("shop_id")
    val shopId: Int,
    @SerializedName("shop_name")
    val shopName: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("token")
    val token: String,
    @SerializedName("VatRegNumber")
    val vatRegNumber: String
)