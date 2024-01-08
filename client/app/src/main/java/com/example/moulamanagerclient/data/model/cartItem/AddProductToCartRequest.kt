package com.example.moulamanagerclient.data.model.cartItem

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddProductToCartRequest (
    @Json(name = "barcode")
    val barcode: String,

    @Json(name = "quantity")
    val quantity: Int
)