package com.android.pizzaapp.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View.inflate
import android.view.ViewGroup
import android.view.Window
import com.android.pizzaapp.R
import com.android.pizzaapp.databinding.CustomizePizzaLayoutBinding
import java.lang.ref.WeakReference


class CustomizePizzaDialog(context: Context, private var listener: AddToCartListener) : Dialog(context, R.style.Theme_Dialog) {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = CustomizePizzaLayoutBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        val window = window
        val wlp = window!!.attributes
        wlp.width = ViewGroup.LayoutParams.MATCH_PARENT
        wlp.height = 800
        wlp.gravity = Gravity.BOTTOM
        window.attributes = wlp
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.button.setOnClickListener {
            listener.addToCart()
            dismiss()
        }

    }
}
interface AddToCartListener {
    fun addToCart()
}