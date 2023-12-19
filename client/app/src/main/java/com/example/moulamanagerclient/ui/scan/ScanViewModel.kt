package com.example.moulamanagerclient.ui.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ScanViewModel: ViewModel() {
    private val _ean: MutableStateFlow<String> = MutableStateFlow("")
    private val _amount: MutableStateFlow<String> = MutableStateFlow("1")
    val ean: StateFlow<String> = _ean
    val amount: StateFlow<String> = _amount

    fun setEan(ean: String) {
        _ean.value = ean
    }

    fun setAmount(amount: String) {
        _amount.value = amount
    }

    fun getAmount(): String {
        return _amount.value
    }

    fun reset() {
        _ean.value = ""
        _amount.value = "1"
    }

}