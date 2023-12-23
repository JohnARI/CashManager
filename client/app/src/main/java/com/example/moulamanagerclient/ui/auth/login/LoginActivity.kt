package com.example.moulamanagerclient.ui.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.moulamanagerclient.R
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.ui.theme.Colors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginActivity() {
	val viewModel = hiltViewModel<LoginViewModel>()
	val loginResult by viewModel.loginResult.collectAsState(ApiResult.Initial)
	val inputPassword by viewModel.inputPassword.collectAsState()
	val inputUsername by viewModel.inputUsername.collectAsState()
	val errorMessage by viewModel.errorMessage.collectAsState()

	Scaffold(
		topBar = {
			TopAppBar(
				colors = TopAppBarDefaults.smallTopAppBarColors(
					containerColor = Colors.BLACK_0,
					titleContentColor = Colors.WHITE,
				),
				title = {
					Row(
						verticalAlignment = Alignment.CenterVertically,
						horizontalArrangement = Arrangement.Center,
						modifier = Modifier
							.fillMaxWidth()
							.wrapContentSize()
					) {
						Image(
							painter = painterResource(id = R.drawable.logo_full_white),
							contentDescription = "Logo",
						)
					}
				}
			)
		}
	) { paddingValues ->
		Surface(
			modifier = Modifier
				.fillMaxSize()
				.padding(paddingValues),
			color = Colors.BLACK_1
		) {
			Row(
				verticalAlignment = Alignment.CenterVertically,
				horizontalArrangement = Arrangement.Center,
				modifier = Modifier
					.wrapContentSize()
			) {
				LoginComponent(
					inputUsername = inputUsername,
					onUsernameChange = { viewModel.setUsername(it) },
					inputPassword = inputPassword,
					onPasswordChange = { viewModel.setPassword(it) },
					onLoginClick = { viewModel.performLogin() },
					loginResult = loginResult,
					errorMessage = errorMessage
				)
			}
		}
	}
}