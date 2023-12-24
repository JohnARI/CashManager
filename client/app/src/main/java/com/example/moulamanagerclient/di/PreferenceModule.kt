package com.example.moulamanagerclient.di

import android.content.Context
import com.example.moulamanagerclient.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

	@Provides
	fun providePreferenceManager(@ApplicationContext appContext: Context): PreferenceManager {
		return PreferenceManager(appContext)
	}
}