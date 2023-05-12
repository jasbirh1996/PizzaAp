package com.android.pizzaapp.data.remote

import com.android.pizzaapp.data.remote.model.PizzaAppResponse
import retrofit2.Response

import retrofit2.http.GET

interface ApiService {

    @GET("v1/pizza/1")
    suspend fun getPizzaData(): Response<PizzaAppResponse>
}