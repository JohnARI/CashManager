package com.example.moulamanagerclient.data.network

import okhttp3.Interceptor
import okhttp3.Response
import android.content.Context
import com.example.moulamanagerclient.utils.SharedPreferences

class AuthInterceptor(private val context: Context) : Interceptor {
	override fun intercept(chain: Interceptor.Chain): Response {
		val originalRequest = chain.request()

		// We don't want to add the token to the login and register requests
		if (originalRequest.url.encodedPath.contains("login") ||
			originalRequest.url.encodedPath.contains("register")) {
			return chain.proceed(originalRequest)
		}

		val token = SharedPreferences.getKey(context, "token")

		val requestBuilder = originalRequest.newBuilder()
			.header("Authorization", "Bearer $token")
			.method(originalRequest.method, originalRequest.body)

		return chain.proceed(requestBuilder.build())
	}
}