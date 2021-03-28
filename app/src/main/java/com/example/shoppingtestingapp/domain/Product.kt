package com.example.shoppingtestingapp.domain


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("alert_qty")
    val alertQty: Int,
    @SerializedName("barcode")
    val barcode: String,
    @SerializedName("brand_id")
    val brandId: Any,
    @SerializedName("category_id")
    val categoryId: Int,
    @SerializedName("expiry_date")
    val expiryDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("product_id")
    val productId: Int,
    @SerializedName("qty")
    val qty: Int,
    @SerializedName("sell_price")
    val sellPrice: Double,
    @SerializedName("sell_price_with_vat")
    val sellPriceWithVat: Double,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("vat")
    val vat: Int
)