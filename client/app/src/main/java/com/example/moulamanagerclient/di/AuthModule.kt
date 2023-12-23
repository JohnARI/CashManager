package com.example.moulamanagerclient.di

import android.content.Context
import com.example.moulamanagerclient.data.network.ApiService
import com.example.moulamanagerclient.data.network.AuthInterceptor
import com.example.moulamanagerclient.data.repositories.auth.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

	@Provides
	@Singleton
	fun provideAuthInterceptor(@ApplicationContext appContext: Context): AuthInterceptor {
		return AuthInterceptor(appContext)
	}

	@Provides
	@Singleton
	fun provideAuthRepository(apiService: ApiService): AuthRepository {
		return AuthRepository(apiService)
	}
}