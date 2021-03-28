package com.example.shoppingtestingapp.domain.model.registration


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("token")
    val token: String
)