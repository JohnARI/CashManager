package com.example.moulamanagerclient.ui.cart

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.shared.AppRoutes
import com.example.moulamanagerclient.ui.auth.composables.AuthContainerBorder
import com.example.moulamanagerclient.ui.auth.composables.AuthTopBar

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
	) { paddingValues ->
		AuthContainerBorder(
			paddingValues = paddingValues,
			content = {
				CartComponent(
					setUpdateQuantity = { viewModel.onInputChange(it) },
				)
			}
		)
	}
}
