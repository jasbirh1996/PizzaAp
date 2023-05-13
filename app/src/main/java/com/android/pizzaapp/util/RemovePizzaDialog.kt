package com.android.pizzaapp.util

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.pizzaapp.R
import com.android.pizzaapp.data.remote.model.SelectedItem
import com.android.pizzaapp.databinding.DialogRemoveFromCartBinding
import com.android.pizzaapp.ui.view.adapter.CartAdapter

class RemovePizzaDialog(
    context: Context,
    var selectedItemList: MutableList<SelectedItem>,
    private var listener: RemoveItemFromCart

) : Dialog(context, R.style.Theme_Dialog) {
    private val binding = DialogRemoveFromCartBinding.inflate(layoutInflater)
    private var mAdapter = CartAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(binding.root)
        initDialogView()

       selectedItemList?.let {
           mAdapter.setSelectedItemList(it)
       }
        mAdapter.cartListener(object : CartAdapter.AdapterRemoveItemListener{
            override fun removeItem(selectedItem: SelectedItem?, pos: Int) {
                listener.removeItem(selectedItem)
                mAdapter.notifyItemChanged(pos)
            }


        })
        binding.rvCustomizePizza.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapter
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
}

interface RemoveItemFromCart{

    fun removeItem(item : SelectedItem?)
}