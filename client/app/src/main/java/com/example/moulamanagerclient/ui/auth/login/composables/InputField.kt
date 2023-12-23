package com.example.moulamanagerclient.ui.auth.login.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
	label: String,
	value: String,
	onValueChange: (String) -> Unit,
	isPassword: Boolean = false
) {
	val (showPassword, setShowPassword) = remember { mutableStateOf(false) }

	OutlinedTextField(
		colors = TextFieldDefaults.outlinedTextFieldColors(
			textColor = Colors.BLACK_4,
			focusedBorderColor = colorResource(id = R.color.black_4),
			unfocusedBorderColor = colorResource(id = R.color.black_4),
		),
		value = value,
		onValueChange = onValueChange,
		label = { Text(text = label) },
		visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
		trailingIcon = {
			if (isPassword) {
				IconButton(onClick = { setShowPassword(!showPassword) }) {
					Icon(
						imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
						contentDescription = if (showPassword) "Hide password" else "Show password"
					)
				}
			}
		}
	)
}