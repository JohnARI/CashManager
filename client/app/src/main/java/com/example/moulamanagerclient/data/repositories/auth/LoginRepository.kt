package com.example.moulamanagerclient.data.repositories.auth

import com.example.moulamanagerclient.data.model.auth.LoginRequest
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.utils.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class AuthRepository {
    suspend fun login(request: LoginRequest): LoginResponse? {
        try {
            val response = CoroutineScope(Dispatchers.IO).async {
                Retrofit.apiService.login(request).body()
            }.await()

            return response?.let {
                LoginResponse(
                    username = it.username,
                    accessToken = it.accessToken,
                    email = it.email,
                    id= it.id,
                )
            }
        } catch (e: Exception) {
            throw e
        }
    }

}