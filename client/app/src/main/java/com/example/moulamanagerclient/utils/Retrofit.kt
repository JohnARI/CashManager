package com.example.moulamanagerclient.utils

import android.app.VoiceInteractor
import android.content.Context
import android.util.Log
import com.example.moulamanagerclient.data.repositories.ApiHeader
import com.example.moulamanagerclient.data.repositories.ApiService
import com.example.moulamanagerclient.data.repositories.ApiURL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {
    private val builder:OkHttpClient.Builder = OkHttpClient().newBuilder();
    init {
        builder.connectTimeout(3,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(25,TimeUnit.SECONDS)
            .addInterceptor{chain ->
                val newRequest = ApiHeader.getAccessToken.let {
                    chain.request().newBuilder()
                        .addHeader("Authorization", it)
                        .build()
            }
        chain.proceed(newRequest)
        }
    }

    private val client = builder.build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(ApiURL.BASE_URL)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val apiService = retrofit.create(ApiService::class.java);
}