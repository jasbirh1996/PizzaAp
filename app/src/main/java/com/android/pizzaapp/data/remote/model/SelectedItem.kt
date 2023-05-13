package com.android.pizzaapp.data.remote.model

data class SelectedItem(
    val crustId: Int,
    val sizeId: Int,
    val quantity: Int?,
    val crustName: String,
    val size: String,
    val price: Double
)