package com.android.pizzaapp.data.remote

sealed class NetworkHandler<T> {
    data class Success<T>(val data: T) : NetworkHandler<T>()
    data class Error<T>( val exception: String?) : NetworkHandler<T>()
}