package com.example.moulamanagerclient.di

import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.data.model.cartItem.UpdateCartItemRequest
import com.example.moulamanagerclient.data.network.ApiHelper
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.data.network.ApiService
import com.example.moulamanagerclient.data.network.AuthInterceptor
import com.example.moulamanagerclient.data.repositories.auth.AuthRepository
import com.example.moulamanagerclient.data.repositories.cartItem.CartItemRepository
import com.example.moulamanagerclient.data.repositories.products.ProductRepository
import com.example.moulamanagerclient.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CartItemModule {
	@Provides
	@Singleton
	fun provideCartItemRepository(apiService: ApiService): CartItemRepository {
		return CartItemRepository(apiService)
	}
}