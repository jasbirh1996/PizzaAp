package com.android.pizzaapp.data.repository

import com.android.pizzaapp.data.remote.ApiService
import com.android.pizzaapp.data.remote.NetworkHandler
import com.android.pizzaapp.data.remote.model.PizzaAppResponse
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import javax.inject.Inject

class AppRepository @Inject constructor(val apiService: ApiService) {

    suspend fun getPizzaData(): NetworkHandler<PizzaAppResponse> {
        val apiData = apiService.getPizzaData()
        val isSuccess = apiData.isSuccessful

        return if (isSuccess && apiData.body() != null) {
            NetworkHandler.Success(apiData.body()!!)
        } else {
            val errorMessage = try {
                JSONObject(apiData.errorBody().toString()).getString("message")
            } catch (e: JSONException) {
                "Error ${e.message.toString()}"
            }

            NetworkHandler.Error(errorMessage)

        }

    }

}