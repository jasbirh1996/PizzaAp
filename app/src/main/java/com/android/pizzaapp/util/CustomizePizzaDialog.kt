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
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.android.pizzaapp.R
import com.android.pizzaapp.data.remote.model.PizzaAppResponse
import com.android.pizzaapp.data.remote.model.SelectedItem
import com.android.pizzaapp.databinding.CustomizePizzaLayoutBinding
import com.android.pizzaapp.ui.view.MainActivity
import com.android.pizzaapp.ui.view.PizzaFragment
import com.android.pizzaapp.ui.viewModel.AppViewModel
import okhttp3.internal.notify
import java.lang.ref.WeakReference


class CustomizePizzaDialog(
    context: Context,
    var crust: List<PizzaAppResponse.Crust>,
    var defaultCrust : Int,
    private var listener: AddToCartListener

) : Dialog(context, R.style.Theme_Dialog) {
   private val binding = CustomizePizzaLayoutBinding.inflate(layoutInflater)
    var crustList = arrayListOf<String>()
    private var selectedItem: SelectedItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        initDialogView()

        crust.forEach {
            crustList.add(it.name)
        }

        setupCrustSpinner(crustList)

        binding.button.setOnClickListener {
            listener.addToCart(selectedItem)
            dismiss()
        }

    }
    private fun initDialogView(){
        val window = window
        val wlp = window!!.attributes
        wlp.width = ViewGroup.LayoutParams.MATCH_PARENT
        wlp.height = 800
        wlp.gravity = Gravity.BOTTOM
        window.attributes = wlp
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

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
                setUpSizeSpinner(sizeList,index,position)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }

        }
    }

    private fun setUpSizeSpinner(list: ArrayList<String>, defaultSize : Int,currentCrustPosition: Int) {
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
                var currentSelectedCrust = crust.get(currentCrustPosition)
                var currentSelectedSize = crust.get(currentCrustPosition).sizes.get(position)
           selectedItem  = SelectedItem(
               crustId = currentSelectedCrust.id,
               sizeId = currentSelectedSize.id,
               quantity = 1,
               crustName = currentSelectedCrust.name,
               size = currentSelectedSize.name,
               price = currentSelectedSize.price

           )
                var price =  "₹ ${currentSelectedSize.price}"

                binding.tvCost.text = price
                binding.tvCost.invalidate()


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }

        }
    }



}

interface AddToCartListener {
    fun addToCart(selectedItem: SelectedItem?)
}
