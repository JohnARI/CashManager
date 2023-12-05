package com.example.moulamanagerclient.data.model.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginResponse(
    @Json(name = "username")
    var username: String,
    @Json(name = "accessToken")
    var accessToken: String,
    @Json(name = "email")
    var email: String,
    @Json(name = "id")
    var id: Int
    )