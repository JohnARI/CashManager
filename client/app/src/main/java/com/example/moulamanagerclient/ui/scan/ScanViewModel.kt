package com.example.moulamanagerclient.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moulamanagerclient.data.model.auth.LoginRequest
import com.example.moulamanagerclient.data.model.auth.LoginResponse
import com.example.moulamanagerclient.data.model.product.ProductResponse
import com.example.moulamanagerclient.data.repositories.ApiHeader
import com.example.moulamanagerclient.data.repositories.auth.AuthRepository
import com.example.moulamanagerclient.data.repositories.products.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ScanViewModel: ViewModel() {
    private val _productResult: MutableStateFlow<ProductResponse?> = MutableStateFlow(null)
    private val _createProductResult: MutableStateFlow<ProductResponse?> = MutableStateFlow(null)
    private val _ean: MutableStateFlow<String> = MutableStateFlow("")
    private val _amount: MutableStateFlow<String> = MutableStateFlow("1")
    private val _crateProductName: MutableStateFlow<String> = MutableStateFlow("")
    private val _crateProductPrice: MutableStateFlow<String> = MutableStateFlow("")
    private val _crateProductDescription: MutableStateFlow<String> = MutableStateFlow("")
    val ean: StateFlow<String> = _ean
    val amount: StateFlow<String> = _amount
    val productResult: StateFlow<ProductResponse?> = _productResult
    val createProductResult: StateFlow<ProductResponse?> = _createProductResult
    val createProductName: StateFlow<String> = _crateProductName
    val createProductPrice: StateFlow<String> = _crateProductPrice
    val createProductDescription: StateFlow<String> = _crateProductDescription

    fun setEan(ean: String) {
        _ean.value = ean
    }

    fun setAmount(amount: String) {
        _amount.value = amount
    }

    fun setCreateProductName(name: String) {
        _crateProductName.value = name
    }

    fun setCreateProductPrice(price: String) {
        _crateProductPrice.value = price
    }

    fun setCreateProductDescription(description: String) {
        _crateProductDescription.value = description
    }

    fun getAmount(): String {
        return _amount.value
    }

    fun reset() {
        _ean.value = ""
        _amount.value = "1"
    }

    fun getProduct(barcode: String) {
        val productRepo = ProductRepository()

        viewModelScope.launch {
            val response = productRepo.getProductByBarcode(barcode)

            withContext(Dispatchers.Main) {
                _productResult.value = response
                Log.d("Product", response.toString())
            }
        }
    }

    fun createProduct(barcode: String, name: String, price: Double, description: String) {
        val productRepo = ProductRepository()

        viewModelScope.launch {
            val response = productRepo.createProduct(barcode, name, price, description)

            withContext(Dispatchers.Main) {
                _createProductResult.value = response
                Log.d("Create Product", response.toString())
            }
        }
    }

}