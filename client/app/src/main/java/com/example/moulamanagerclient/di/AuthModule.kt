package com.example.moulamanagerclient.di

import com.example.moulamanagerclient.data.network.ApiService
import com.example.moulamanagerclient.data.network.AuthInterceptor
import com.example.moulamanagerclient.data.repositories.auth.AuthRepository
import com.example.moulamanagerclient.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

	@Provides
	@Singleton
	fun provideAuthInterceptor(preferences: PreferenceManager): AuthInterceptor {
		return AuthInterceptor(preferences)
	}

	@Provides
	@Singleton
	fun provideAuthRepository(apiService: ApiService): AuthRepository {
		return AuthRepository(apiService)
	}
}