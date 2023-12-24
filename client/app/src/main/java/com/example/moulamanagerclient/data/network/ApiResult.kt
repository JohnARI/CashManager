package com.example.moulamanagerclient.data.network

data class ErrorInfo(val status: Int, val message: String)

sealed class ApiResult<out T> {
	object Initial : ApiResult<Nothing>()
	data class Success<out T>(val data: T?) : ApiResult<T>()
	data class Error(val errorInfo: ErrorInfo) : ApiResult<Nothing>()
}