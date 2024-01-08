package com.example.moulamanagerclient.ui.auth.login

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.moulamanagerclient.ui.auth.composables.*

@Composable
fun LoginComponent(
	title: String,
	inputUsername: String,
	onUsernameChange: (String) -> Unit,
	inputPassword: String,
	onPasswordChange: (String) -> Unit,
	onLoginClick: () -> Unit,
	onRegisterClick: () -> Unit,
	errorMessage: String,
	isLoading: Boolean,
) {
	AuthContainer {
		AuthTitle(title)
		Spacer(modifier = Modifier.height(10.dp))
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
		AuthButton(
			onClick = onLoginClick,
			label = "Sign in",
			isLoading = isLoading,
		)

		DividerWithText(text = "OR")

		AuthButton(
			onClick = onRegisterClick,
			label = "Sign up",
			isLoading = false,
			borderColor = Color.Gray
		)
	}
}