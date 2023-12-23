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
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.ui.theme.Colors

@Composable
fun LoginButton(
	loginResult: ApiResult<LoginResponse?>,
	onLoginClick: () -> Unit,
	label: String
) {
	OutlinedButton(
		shape = RoundedCornerShape(10),
		border = BorderStroke(1.dp, Colors.YELLOW_1),
		onClick = onLoginClick,
	) {
		Box(contentAlignment = Alignment.Center) {
			when (loginResult) {
				is ApiResult.Loading -> CircularProgressIndicator(color = Colors.YELLOW_1, modifier = androidx.compose.ui.Modifier.size(20.dp))
				else -> Text(label, color = Colors.WHITE)
			}
		}
	}
}