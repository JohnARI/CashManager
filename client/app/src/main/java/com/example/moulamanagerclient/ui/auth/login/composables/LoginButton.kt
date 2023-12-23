package com.example.moulamanagerclient.ui.auth.login.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.ui.theme.Colors

@Composable
fun LoginButton(
	loginResult: ApiResult<LoginResponse?>,
	onLoginClick: () -> Unit
) {
	OutlinedButton(
		shape = RoundedCornerShape(10),
		border = BorderStroke(1.dp, Colors.YELLOW_1),
		onClick = onLoginClick
	) {
		if (loginResult is ApiResult.Loading) {
			CircularProgressIndicator(color = Colors.WHITE)
		} else {
			Text("Login", color = Colors.WHITE)
		}
	}
}