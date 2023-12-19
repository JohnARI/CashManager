package com.example.moulamanagerclient.ui.login

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moulamanagerclient.data.model.auth.LoginRequest
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.repositories.ApiHeader
import com.example.moulamanagerclient.data.repositories.auth.AuthRepository
import com.example.moulamanagerclient.utils.Retrofit
import com.example.moulamanagerclient.utils.SharedPreferences
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: ViewModel() {
    private val _loginResult: MutableStateFlow<LoginResponse?> = MutableStateFlow(null)
    private val _inputPassword:MutableStateFlow<String> = MutableStateFlow("")
    private val _inputUsername:MutableStateFlow<String> = MutableStateFlow("")

    val loginResult: StateFlow<LoginResponse?> = _loginResult
    val inputPassword: StateFlow<String> = _inputPassword
    val inputUsername: StateFlow<String> = _inputUsername

    fun setUsername(username:String){
        _inputUsername.value = username;
    }
    fun setPassword(password:String){
        _inputPassword.value = password;
    }
    fun performLogin(context: Context) {
        val auth = AuthRepository()
        val request = LoginRequest(
            password = _inputPassword.value,
            username = _inputUsername.value
        )

        viewModelScope.launch {
            val response = auth.login(request)
            withContext(Dispatchers.Main) {
                _loginResult.value = response
            }
            SharedPreferences.setValue(context, "Authorization", ApiHeader.getAccessToken)
            response?.token?.let { ApiHeader.setAccessToken(it) };

//            token?.let{
//                ApiHeader.setAccessToken(it)
//        }
        }
    }
}

