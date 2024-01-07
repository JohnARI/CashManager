package com.example.moulamanagerclient.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moulamanagerclient.data.model.Pagination
import com.example.moulamanagerclient.data.model.auth.LoginRequest
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.data.model.cartItem.UpdateCartItemRequest
import com.example.moulamanagerclient.data.model.product.ProductResponse
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.data.repositories.auth.AuthRepository
import com.example.moulamanagerclient.data.repositories.cartItem.CartItemRepository
import com.example.moulamanagerclient.utils.PreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel
@Inject
constructor(
	private val cartItemRepository: CartItemRepository,
) : ViewModel() {
	private val _itemUpdateResult: MutableStateFlow<ApiResult<CartItem?>> = MutableStateFlow(ApiResult.Initial)
	private val _itemListResult: MutableStateFlow<ApiResult<Pagination<CartItem>?>?> = MutableStateFlow(null)
	private val _itemList: MutableStateFlow<List<CartItem>?> = MutableStateFlow(null)
	private val _updatedItem: MutableStateFlow<CartItem?> = MutableStateFlow(null)
	private val _itemIdToUpdate: MutableStateFlow<Int> = MutableStateFlow(0)
	private val _errorMessage: MutableStateFlow<String> = MutableStateFlow("")
	private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)


	val itemList: StateFlow<List<CartItem>?> = _itemList
	val itemListResult: StateFlow<ApiResult<Pagination<CartItem>?>?> = _itemListResult
	val updatedItem: StateFlow<CartItem?> = _updatedItem
	val itemUpdateResult: StateFlow<ApiResult<CartItem?>> = _itemUpdateResult
	val errorMessage: StateFlow<String> = _errorMessage
	val isLoading: StateFlow<Boolean> = _isLoading

	fun onInputChange(updatedItem: CartItem) {
		_updatedItem.value = updatedItem
	}

	fun getItemList() {
		viewModelScope.launch {
			_isLoading.value = true
			_errorMessage.value = ""

			when (val result = cartItemRepository.getCartItem()) {
				is ApiResult.Success -> {
					_itemList.value = result.data?.content
				}

				is ApiResult.Error -> {
					_errorMessage.value = result.errorInfo.message
				}

				ApiResult.Initial -> {
					_errorMessage.value = ""
				}

				else -> {

				}
			}

			_isLoading.value = false
		}
	}
	fun updateItemList() {
		val body = _updatedItem.value?.let {
			UpdateCartItemRequest(
				quantity = it.quantity
			)
		}!!

		viewModelScope.launch {
			_isLoading.value = true
			_errorMessage.value = ""

			_itemUpdateResult.value = cartItemRepository.updateCartItem(
				body,
				_itemIdToUpdate.value
			)

			when (val result = _itemUpdateResult.value) {
				is ApiResult.Success -> {

				}

				is ApiResult.Error -> {
					_errorMessage.value = result.errorInfo.message
				}

				ApiResult.Initial -> {
					_errorMessage.value = ""
				}
			}

			_isLoading.value = false
		}
	}
}