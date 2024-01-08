package com.example.moulamanagerclient.ui.payment

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.ui.theme.Colors
import com.stripe.android.paymentsheet.PaymentSheet
import kotlinx.coroutines.launch

@Composable
fun PaymentButton(
	viewModel: PaymentViewModel,
	paymentSheet: PaymentSheet,
	paymentIntentClientSecret: String?,
	customerConfig: PaymentSheet.CustomerConfiguration?
) {
	val coroutineScope = rememberCoroutineScope()

	Button(
		shape = RoundedCornerShape(10),
		border = BorderStroke(1.dp, Colors.YELLOW_1),
		modifier = Modifier
			.width(150.dp)
			.height(50.dp)
		,
		onClick = {
			coroutineScope.launch {
				viewModel.fetchPaymentIntentAndCustomerConfig()
			}
			if (customerConfig != null && paymentIntentClientSecret != null) {
				presentPaymentSheet(paymentSheet, customerConfig, paymentIntentClientSecret)
			}
		}
	) {
		Text("Checkout")
	}
}

private fun presentPaymentSheet(
	paymentSheet: PaymentSheet,
	customerConfig: PaymentSheet.CustomerConfiguration,
	paymentIntentClientSecret: String
) {
	paymentSheet.presentWithPaymentIntent(
		paymentIntentClientSecret,
		PaymentSheet.Configuration(
			merchantDisplayName = "Moula Manager",
			customer = customerConfig,
			allowsDelayedPaymentMethods = true
		)
	)
}