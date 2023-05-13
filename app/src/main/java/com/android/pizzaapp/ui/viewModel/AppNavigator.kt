package com.android.pizzaapp.ui.viewModel

import androidx.fragment.app.Fragment
import com.android.pizzaapp.data.remote.model.PizzaAppResponse
import com.android.pizzaapp.data.remote.model.SelectedItem

interface AppNavigator {

    fun switchFragment()
    fun selectedItemList(item : SelectedItem?)


}