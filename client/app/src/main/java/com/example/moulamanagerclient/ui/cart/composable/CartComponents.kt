package com.example.moulamanagerclient.ui.cart.composable

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.data.model.product.ProductResponse
import com.example.moulamanagerclient.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemRow(
    cartItem: CartItem,
    isOdd: Boolean,
    onValueChange: (CartItem) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .height(75.dp),
        color = when(isOdd) {
            true -> Colors.BLACK_1
            false -> Colors.BLACK_2
        }
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ){
                InputField(cartItem, cartItem.quantity.toString(),  onValueChange)
                Text("x", color = Colors.WHITE)
                Text(cartItem.product.name, color = Colors.WHITE)
            }
            Text(cartItem.product.price.toString() + " €", color = Colors.WHITE)
        }
    }
}

@Composable
fun InputField(
    cartItem: CartItem,
    value: String,
    onValueChange: (CartItem) -> Unit,
) {
    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.black_4),
            unfocusedBorderColor = colorResource(id = R.color.black_4),
            focusedTextColor = colorResource(id = R.color.white),
            unfocusedTextColor = colorResource(id = R.color.white),
        ),
        placeholder = {
            Text(value, color = Colors.WHITE)
        },
        modifier = Modifier
            .width(70.dp)
            .height(60.dp)
            .padding(bottom = 10.dp),
        value = value,
        onValueChange = {
            newValue -> onValueChange(
                CartItem(
                    id = cartItem.id,
                    cart = cartItem.cart,
                    product = cartItem.product,
                    quantity = newValue.toInt()
                )
            )
        },
    )
}
