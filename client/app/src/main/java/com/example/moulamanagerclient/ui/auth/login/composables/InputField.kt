package com.example.moulamanagerclient.ui.auth.login.composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
	label: String,
	value: String,
	onValueChange: (String) -> Unit
) {
	OutlinedTextField(
		colors = TextFieldDefaults.outlinedTextFieldColors(
			textColor = Colors.BLACK_4,
			focusedBorderColor = colorResource(id = R.color.black_4),
			unfocusedBorderColor = colorResource(id = R.color.black_4),
		),
		value = value,
		onValueChange = onValueChange,
		label = { Text(text = label) },
	)
}