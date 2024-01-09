package com.example.moulamanagerclient.data.repositories.cartItem

import com.example.moulamanagerclient.data.model.Pagination
import com.example.moulamanagerclient.data.model.auth.LoginRequest
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.model.cartItem.AddProductToCartRequest
import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.data.model.cartItem.UpdateCartItemRequest
import com.example.moulamanagerclient.data.model.product.ProductResponse
import com.example.moulamanagerclient.data.network.ApiHelper
import com.example.moulamanagerclient.data.network.ApiResult
import com.example.moulamanagerclient.data.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartItemRepository (
    private val apiService: ApiService
){
    suspend fun getCartItem(): ApiResult<Pagination<CartItem>?> {
        return withContext(Dispatchers.IO) {
            ApiHelper.handleApiResponse(
                request = { apiService.getCartItem() }
            )
        }
    }
    suspend fun deleteCartItem(itemId: Int): ApiResult<Any> {
        return withContext(Dispatchers.IO) {
            ApiHelper.handleApiResponse(
                request = { apiService.deleteCartItem(itemId) }
            )
        }
    }
    suspend fun updateCartItem(request: UpdateCartItemRequest, itemId: Int): ApiResult<CartItem?> {
        return withContext(Dispatchers.IO) {
            ApiHelper.handleApiResponse { apiService.updateCartItem(itemId, request) }
        }
    }

    suspend fun addProduct(product: AddProductToCartRequest, barcode: String): ApiResult<ProductResponse> {
        return withContext(Dispatchers.IO) {
            ApiHelper.handleApiResponse(
                request = { apiService.addProductToCartItem(product, barcode) }
            )
        }
    }
}