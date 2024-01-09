package com.example.moulamanagerclient.data.network

import com.example.moulamanagerclient.data.model.Pagination
import com.example.moulamanagerclient.data.model.auth.LoginRequest
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.model.auth.RegisterRequest
import com.example.moulamanagerclient.data.model.cartItem.CartItem
import com.example.moulamanagerclient.data.model.cartItem.UpdateCartItemRequest
import com.example.moulamanagerclient.data.model.cartItem.AddProductToCartRequest
import com.example.moulamanagerclient.data.model.payment.PaymentIntentResponse
import com.example.moulamanagerclient.data.model.product.CreateProductRequest
import com.example.moulamanagerclient.data.model.product.ProductResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET(ApiEndpoints.BARCODE)
    suspend fun getProductsByBarcode(@Path("barcode") barcode: String): Response<ProductResponse>

    @GET(ApiEndpoints.PRODUCTS)
    suspend fun getProducts(@Query("page") page: Int, @Query("size") size: Int): Response<Pagination<ProductResponse>>

	@GET(ApiEndpoints.GET_CART_ITEM)
	suspend fun getCartItem(): Response<Pagination<CartItem>>

	@PATCH(ApiEndpoints.UPDATE_CART_ITEM)
	suspend fun updateCartItem(@Path("productId") productId: Int, @Body request: UpdateCartItemRequest): Response<CartItem>

	@DELETE(ApiEndpoints.DELETE_CART_ITEM)
	suspend fun deleteCartItem(@Path("productId") productId: Int): Response<Any>

	@GET(ApiEndpoints.PRODUCTS_BY_NAME)
	suspend fun searchProductsByName(
		@Path("name") name: String,
		@Query("page") page: Int,
		@Query("size") size: Int
	): Response<Pagination<ProductResponse>>
    @POST(ApiEndpoints.PRODUCTS)
    suspend fun createProduct(@Body request: CreateProductRequest): Response<ProductResponse>

    @POST(ApiEndpoints.LOGIN)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST(ApiEndpoints.CART_ITEMS_BARCODE)
    suspend fun addProductToCartItem(
        @Body request: AddProductToCartRequest,
        @Path("barcode") barcode: String
    ): Response<ProductResponse>

    @POST(ApiEndpoints.REGISTER)
    suspend fun register(@Body request: RegisterRequest): Response<LoginResponse>

    @POST(ApiEndpoints.CREATE_PAYMENT_INTENT)
    suspend fun createPaymentIntent(): Response<PaymentIntentResponse>
}
