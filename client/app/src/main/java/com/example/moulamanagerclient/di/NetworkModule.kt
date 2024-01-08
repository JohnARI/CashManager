package com.example.moulamanagerclient.di


import com.example.moulamanagerclient.data.network.ApiEndpoints
import com.example.moulamanagerclient.data.network.ApiService
import com.example.moulamanagerclient.data.network.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	@Singleton
	fun provideApiService(authInterceptor: AuthInterceptor): ApiService {
		val loggingInterceptor = HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		}

		val client = OkHttpClient.Builder()
			.addInterceptor(authInterceptor)
			.addInterceptor(loggingInterceptor)
			.connectTimeout(3, TimeUnit.SECONDS)
			.readTimeout(20, TimeUnit.SECONDS)
			.writeTimeout(25, TimeUnit.SECONDS)
			.build()

		return Retrofit.Builder()
			.baseUrl(ApiEndpoints.BASE_URL)
			.client(client)
			.addConverterFactory(MoshiConverterFactory.create())
			.build()
			.create(ApiService::class.java)
	}
}