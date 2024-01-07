package com.example.moulamanagerclient.ui.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.moulamanagerclient.data.model.cart.Cart
import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.data.model.product.ProductResponse
import com.example.moulamanagerclient.ui.cart.composable.CartItemRow
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Composable
fun CartComponent(
    setUpdateQuantity: (CartItem) -> Unit,
) {
    Column{
        CartItemRow(
            CartItem(
               id = 1,
                product = ProductResponse(
                    id= 2,
                    name = "Vittel2",
                    price = 122.43,
                    description = "Dans 20-30 ans y'en aura plus",
                    barcode = "3048431001911"
                ),
                quantity = 0,
                cart = Cart(
                    id= 1,
                    userId= 1,
                    createdAt=
                            "2024-01-07T16:58:12.205+00:00",

                    checkedOut= false
                )
            ),
            true,
            setUpdateQuantity,
            "3")
        Text("Cart")
        Text("Cart")
    }
}