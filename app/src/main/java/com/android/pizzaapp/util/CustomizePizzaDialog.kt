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
import com.android.pizzaapp.data.remote.model.PizzaAppResponse
import com.android.pizzaapp.databinding.CustomizePizzaLayoutBinding
import com.android.pizzaapp.ui.view.PizzaFragment
import com.android.pizzaapp.ui.viewModel.AppViewModel
import java.lang.ref.WeakReference


class CustomizePizzaDialog(
    context: Context,
    var crust: List<PizzaAppResponse.Crust>,
    var defaultCrust : Int,
    private var listener: AddToCartListener
) : Dialog(context, R.style.Theme_Dialog) {
   private val binding = CustomizePizzaLayoutBinding.inflate(layoutInflater)
    var crustList = arrayListOf<String>()
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


        crust.forEach {
            crustList.add(it.name)
        }

        setupCrustSpinner(crustList)


        binding.button.setOnClickListener {
            listener.addToCart()
            dismiss()
        }

    }

    private fun setupCrustSpinner(list: ArrayList<String>) {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            list,

            )

        binding.spCrust.adapter = adapter

        val index = crust.indexOfFirst { it.id == defaultCrust }

        binding.spCrust.setSelection(index)


        binding.spCrust.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                // logic on selection
                var data = parent?.getItemAtPosition(position) as String
                Log.e("spinnerCrust", data)


                var sizeList = arrayListOf<String>()
                crust.get(position).sizes.forEach {
                    sizeList.add(it.name)
                }
                val index = crust.get(position).sizes.indexOfFirst { it.id == crust.get(position).defaultSize }
                setUpSizeSpinner(sizeList,index)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }

        }
    }

    private fun setUpSizeSpinner(list: ArrayList<String>, defaultSize : Int) {
        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            list,

            )

        binding.spSize.adapter = adapter
        binding.spSize.setSelection(defaultSize)
        binding.spSize.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {
                // logic on selection
                var data = parent?.getItemAtPosition(position) as String
                Log.e("spinnerSize", data)
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