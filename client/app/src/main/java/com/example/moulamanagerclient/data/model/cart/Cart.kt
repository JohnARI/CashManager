package com.example.moulamanagerclient.data.model.cart
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class Cart(
	@Json(name = "id")
	val id: Int,
	@Json(name = "userId")
	val userId: Int,
	@Json(name = "createdAt")
	val createdAt: String,
	@Json(name = "checkedOut")
	val checkedOut: Boolean
)
