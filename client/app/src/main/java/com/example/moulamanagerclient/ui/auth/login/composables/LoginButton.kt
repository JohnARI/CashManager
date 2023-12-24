package com.example.moulamanagerclient.ui.auth.login.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.ui.theme.Colors

@Composable
fun LoginButton(
	onLoginClick: () -> Unit,
	label: String,
	isLoading: Boolean,
) {
	OutlinedButton(
		shape = RoundedCornerShape(10),
		border = BorderStroke(1.dp, Colors.YELLOW_1),
		onClick = onLoginClick,
		enabled = !isLoading,
	) {
		Box(contentAlignment = Alignment.Center) {
			if (isLoading) {
				CircularProgressIndicator(
					color = Colors.YELLOW_1,
					modifier = Modifier.size(20.dp)
				)
			} else {
				Text(
					text = label,
					color = Colors.WHITE,
				)
			}
		}
	}
}