package com.android.pizzaapp.data.remote.model

data class PizzaAppResponse(
    val crusts: List<Crust>,
    val defaultCrust: Int,
    val description: String,
    val id: String,
    val isVeg: Boolean,
    val name: String
){
    data class Size(
        val id: Int,
        val name: String,
        val price: Double
    )

    data class Crust(
        val defaultSize: Int,
        val id: Int,
        val name: String,
        val sizes: List<Size>
    )
}



