package com.example.moulamanagerclient.ui.cart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
    cartItemList: List<CartItem>?
) {
    LazyColumn{
        itemsIndexed(cartItemList ?: emptyList()) { index, cartItem ->
                CartItemRow(
                    cartItem = cartItem,
                    isOdd = if (index % 2 == 0) true else false,
                    onValueChange = setUpdateQuantity
                )
        }
    }
}