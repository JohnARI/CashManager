package com.example.moulamanagerclient.data.model.product

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateProductRequest(
    @Json(name = "barcode")
    val barcode: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "price")
    val price: Double,
    @Json(name = "description")
    val description: String
)