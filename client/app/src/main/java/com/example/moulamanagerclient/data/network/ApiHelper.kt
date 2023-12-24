package com.example.moulamanagerclient.data.network

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object ApiHelper {
	suspend fun <T> handleApiResponse(request: suspend () -> Response<T>,): ApiResult<T> {
		return try {
			val response = request()
			if (response.isSuccessful) {
				ApiResult.Success(response.body()!!)
			} else {
				ApiResult.Error(ErrorInfo(response.code(), response.message()))
			}
		} catch (e: Exception) {
			when (e) {
				is HttpException -> {
					ApiResult.Error(ErrorInfo(e.code(), e.message()))
				}
				is SocketTimeoutException -> {
					ApiResult.Error(ErrorInfo(408, "Request timeout"))
				}
				is UnknownHostException -> {
					ApiResult.Error(ErrorInfo(500, "No internet connection"))
				}
				is IOException -> {
					ApiResult.Error(ErrorInfo(500, "No internet connection"))
				}
				else -> {
					ApiResult.Error(ErrorInfo(500, "Something went wrong"))
				}
			}
		}
	}

}