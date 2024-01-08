package com.example.moulamanagerclient.ui.cart.composable

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.twotone.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.data.model.product.ProductResponse
import com.example.moulamanagerclient.ui.theme.Colors
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun CartItemRow(
    cartItem: CartItem,
    isOdd: Boolean,
    onValueChange: (CartItem) -> Unit,
) {
    val delete = SwipeAction(
        icon = rememberVectorPainter(Icons.TwoTone.Delete),
        background = Colors.RED,
        onSwipe = {
            onValueChange(
                CartItem(
                    id = cartItem.id,
                    cart = cartItem.cart,
                    product = cartItem.product,
                    quantity = 0
                )
            )
        }
    )

    SwipeableActionsBox(
        endActions = listOf(delete),
        swipeThreshold = 40.dp,
        backgroundUntilSwipeThreshold = MaterialTheme.colorScheme.surfaceColorAtElevation(40.dp)
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

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun InputField(
    cartItem: CartItem,
    initialValue: String,
    onValueChange: (CartItem) -> Unit,
) {
    var currentValue by remember { mutableStateOf(initialValue) }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = cartItem) {
        currentValue = initialValue
    }

    OutlinedTextField(
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.black_4),
            unfocusedBorderColor = colorResource(id = R.color.black_4),
            focusedTextColor = colorResource(id = R.color.white),
            unfocusedTextColor = colorResource(id = R.color.white),
        ),
        modifier = Modifier
            .width(70.dp)
            .height(60.dp)
            .padding(vertical = 5.dp),
        value = currentValue,
        onValueChange = { newValue -> if(newValue.length > 2) currentValue = "99" else currentValue = newValue },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                val quantity = currentValue.toIntOrNull() ?: 0
                onValueChange(cartItem.copy(quantity = quantity))
                keyboardController?.hide()
            }
        )
    )
}
