package com.android.pizzaapp.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.android.pizzaapp.R
import com.android.pizzaapp.databinding.CustomizePizzaLayoutBinding
import java.lang.ref.WeakReference


class CustomizePizzaDialog(context: Context, private var listener: AddToCartListener) : Dialog(context, R.style.Theme_Dialog) {
    val binding = CustomizePizzaLayoutBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {

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
        setupCrustSpinner(arrayListOf("pizza","size"))
        setUpSizeSpinner(arrayListOf("5","4"))

        binding.button.setOnClickListener {
            listener.addToCart()
            dismiss()
        }

    }
    private fun setupCrustSpinner(list : ArrayList<String>){
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            list,

        )

        binding.spCrust.adapter = adapter
        binding.spCrust.setSelection(1)

        binding.spCrust.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                // logic on selection
                var data = parent?.getItemAtPosition(position) as String
                Log.e("spinnerCrust",data)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }

        }
    }

    private fun setUpSizeSpinner(list : ArrayList<String>){
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            list,

            )

        binding.spSize.adapter = adapter
        binding.spSize.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
             // logic on selection
                var data = parent?.getItemAtPosition(position) as String
                Log.e("spinnerSize",data)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }

        }
    }
}
interface AddToCartListener {
    fun addToCart()
}