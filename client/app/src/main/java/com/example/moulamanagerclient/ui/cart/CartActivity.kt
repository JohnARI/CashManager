package com.example.moulamanagerclient.ui.cart

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.shared.AppRoutes
import com.example.moulamanagerclient.ui.auth.composables.AuthContainerBorder
import com.example.moulamanagerclient.ui.auth.composables.AuthTopBar
import com.example.moulamanagerclient.ui.theme.Colors

@Composable
fun CartActivity() {
	val viewModel = hiltViewModel<CartViewModel>()
	val itemList by viewModel.itemList.collectAsState()
	val errorMessage by viewModel.errorMessage.collectAsState()
	val isLoading by viewModel.isLoading.collectAsState()

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
			CartComponent(
				setUpdateQuantity = { viewModel.onInputChange(it) },
				cartItemList = itemList
			)
		}
	}
}
