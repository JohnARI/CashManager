package com.example.moulamanagerclient.ui.auth.login

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.ui.auth.login.composables.InputField
import com.example.moulamanagerclient.ui.auth.login.composables.LoginButton
import com.example.moulamanagerclient.ui.theme.Colors

@Composable
fun LoginComponent(
	inputUsername: String,
	onUsernameChange: (String) -> Unit,
	inputPassword: String,
	onPasswordChange: (String) -> Unit,
	onLoginClick: () -> Unit,
	loginResult: ApiResult<LoginResponse?>,
	errorMessage: String
) {
	Column(
		horizontalAlignment = Alignment.Start,
		verticalArrangement = Arrangement.spacedBy(10.dp),
		modifier = Modifier
			.wrapContentSize()
			.border(width = 1.dp, color = colorResource(R.color.black_3), shape = RoundedCornerShape(2))
			.padding(20.dp)
	) {
		Text(
			text = "Sign in",
			color = Color.White,
			style = MaterialTheme.typography.titleLarge.copy(
				fontSize = 36.sp,
				fontWeight = FontWeight(700)
			),
			modifier = Modifier.drawBehind {
				val strokeWidthPx = 1.dp.toPx()
				val verticalOffset = size.height - strokeWidthPx / 2

				drawLine(
					color = Colors.YELLOW_1,
					strokeWidth = strokeWidthPx,
					start = Offset(0f, verticalOffset),
					end = Offset(size.width, verticalOffset)
				)
			},
		)
		InputField(
			label = "Username",
			value = inputUsername,
			onValueChange = onUsernameChange
		)
		InputField(
			label = "Password",
			value = inputPassword,
			onValueChange = onPasswordChange,
			isPassword = true
		)
		if (errorMessage.isNotEmpty()) {
			Text(
				text = errorMessage,
				color = Color.Red,
				style = MaterialTheme.typography.bodyMedium
			)
		}
		LoginButton(
			loginResult = loginResult,
			onLoginClick = onLoginClick,
			label = "Sign in"
		)
	}
}