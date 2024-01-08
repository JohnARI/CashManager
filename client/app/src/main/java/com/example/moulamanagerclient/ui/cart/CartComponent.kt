package com.example.moulamanagerclient.ui.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.ui.cart.composable.CartItemRow
import com.example.moulamanagerclient.ui.theme.Colors

@Composable
fun CartComponent(
    setUpdateQuantity: (CartItem) -> Unit,
    cartItemList: List<CartItem>?,
    getTotal: () -> String
) {
    Column{
        BoxWithConstraints {
            val maxHeight = maxHeight - 150.dp
            LazyColumn(modifier = Modifier.heightIn(max = maxHeight)) {
                itemsIndexed(cartItemList ?: emptyList()) { index, cartItem ->
                    CartItemRow(
                        cartItem = cartItem,
                        isOdd = if (index % 2 == 0) true else false,
                        onValueChange = setUpdateQuantity
                    )
                }
            }
        }
        Divider(color = Colors.YELLOW_1, thickness = 1.dp)
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
        ){
            Text("Total", color=Colors.WHITE)
            Text(getTotal() + " €",
                color=Colors.WHITE,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .padding(0.dp)
            )
        }
    }
}