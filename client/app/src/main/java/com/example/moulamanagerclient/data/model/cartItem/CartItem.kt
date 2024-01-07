package com.example.moulamanagerclient.data.model.cartItem

import com.example.moulamanagerclient.data.model.cart.Cart
import com.example.moulamanagerclient.data.model.product.ProductResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CartItem(
	@Json(name = "id")
	val id: Int,
	@Json(name = "cart")
	val cart: Cart,
	@Json(name = "product")
	val product: ProductResponse,
	@Json(name = "quantity")
	val quantity: Int
)



@JsonClass(generateAdapter = true)
data class UpdateCartItemRequest(
	@Json(name = "quantity")
	val quantity: Int,
)
