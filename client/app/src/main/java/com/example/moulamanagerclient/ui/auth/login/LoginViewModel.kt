package com.example.moulamanagerclient.ui.auth.login

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moulamanagerclient.data.model.auth.LoginRequest
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.data.repositories.auth.AuthRepository
import com.example.moulamanagerclient.utils.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/* Suppressing the linter is safe here because the context is a singleton
that lives as long as the app lives so there is no risk of memory leaks here */
@SuppressLint("StaticFieldLeak")
@HiltViewModel
class LoginViewModel
@Inject
constructor(
	private val authRepository: AuthRepository,
	@ApplicationContext private val context: Context
) : ViewModel() {
	private val _loginResult: MutableStateFlow<ApiResult<LoginResponse?>> = MutableStateFlow(ApiResult.Initial)
	private val _inputPassword: MutableStateFlow<String> = MutableStateFlow("")
	private val _inputUsername: MutableStateFlow<String> = MutableStateFlow("")
	private val _errorMessage: MutableStateFlow<String> = MutableStateFlow("")

	val loginResult: StateFlow<ApiResult<LoginResponse?>> = _loginResult
	val inputPassword: StateFlow<String> = _inputPassword
	val inputUsername: StateFlow<String> = _inputUsername
	val errorMessage: StateFlow<String> = _errorMessage


	fun setUsername(username: String) {
		_inputUsername.value = username
	}

	fun setPassword(password: String) {
		_inputPassword.value = password
	}

	fun performLogin() {
		val request = LoginRequest(
			password = _inputPassword.value,
			username = _inputUsername.value
		)

		viewModelScope.launch {
			_errorMessage.value = ""
			_loginResult.value = ApiResult.Loading
			_loginResult.value = authRepository.login(request)

			when (val result = _loginResult.value) {
				is ApiResult.Success -> {
					result.data?.token?.let { token ->
						SharedPreferences.setValue(context, "token", token)
					}
				}

				is ApiResult.Error -> {
					when (result.errorInfo.status) {
						401 -> {
							_errorMessage.value = "Invalid credentials"
						}

						else -> {
							_errorMessage.value = "Login failed"
						}
					}
				}

				is ApiResult.Loading -> {}

				ApiResult.Initial -> {
					_errorMessage.value = ""
				}
			}
		}
	}
}