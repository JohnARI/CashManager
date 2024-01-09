package com.example.moulamanagerclient.ui.cart

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.ui.auth.composables.AuthTopBar
import com.example.moulamanagerclient.ui.payment.PaymentButton
import com.example.moulamanagerclient.ui.payment.PaymentViewModel
import com.example.moulamanagerclient.ui.theme.Colors
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet

@Composable
fun CartActivity() {
	val viewModelPayment: PaymentViewModel = hiltViewModel()
	val viewModel = hiltViewModel<CartViewModel>()
	val itemList by viewModel.itemList.collectAsState()
	val paymentSheet = rememberPaymentSheet(::onPaymentSheetResult)
	val paymentIntentClientSecret by viewModelPayment.paymentIntentClientSecret.collectAsState()
	val customerConfig by viewModelPayment.customerConfig.collectAsState()

	LaunchedEffect(Unit) {
		viewModel.getItemList()
	}

	Scaffold(
		topBar = {
			AuthTopBar(logo = R.drawable.logo_full_white)
		}
	) {paddingValue ->
		Surface(
			modifier = Modifier
				.padding(paddingValue)
				.fillMaxSize(),
			color = Colors.BLACK_1,
		){
			Column{
				CartComponent(
					setUpdateQuantity = { viewModel.onInputChange(it) },
					cartItemList = itemList,
					getTotal = { viewModel.getTotal() },
					getSumForItem = { viewModel.getSumForItem(it) }
				)
				Box(modifier = Modifier
					.padding(horizontal = 20.dp, vertical = 5.dp)
				){
					PaymentButton(viewModelPayment, paymentSheet, paymentIntentClientSecret, customerConfig)
				}
			}
		}
	}
}

private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
	when (paymentSheetResult) {
		is PaymentSheetResult.Canceled -> {
			Log.i("PaymentSheet", "Payment canceled")
		}

		is PaymentSheetResult.Failed -> {
			Log.i("PaymentSheet", "Payment failed")
		}

		is PaymentSheetResult.Completed -> {
			// Display for example, an order confirmation screen
			Log.i("PaymentSheet", "Payment completed")
		}
	}
}
