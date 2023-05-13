package com.android.pizzaapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.android.pizzaapp.R
import com.android.pizzaapp.data.remote.model.SelectedItem
import com.android.pizzaapp.databinding.FragmentPizzaCartBinding
import com.android.pizzaapp.ui.viewModel.AppViewModel
import com.android.pizzaapp.util.RemoveItemFromCart
import com.android.pizzaapp.util.RemovePizzaDialog


class PizzaCartFragment : Fragment() {

    private lateinit var binding : FragmentPizzaCartBinding
    val viewModel : AppViewModel by activityViewModels ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPizzaCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.data = viewModel
        binding.btRemove.setOnClickListener {
            RemovePizzaDialog(requireContext(), viewModel.selectedItemList, object : RemoveItemFromCart {
                override fun removeItem(item: SelectedItem?) {
                    val index = viewModel.selectedItemList.indexOf(item)
                    val quantity = viewModel.selectedItemList.get(index).quantity
                    val price = viewModel.selectedItemList.get(index).price

                    if (quantity <= 1) {
                        viewModel.selectedItemList.remove(item)
                        viewModel.totalPrice = viewModel.totalPrice - price
                        updateValue()

                    } else {
                        viewModel.selectedItemList.get(index).quantity = quantity - 1
                        viewModel.totalPrice = viewModel.totalPrice - price
                        viewModel.totalQuantity = viewModel.totalQuantity - quantity
                        updateValue()

                    }


                }

            }).show()

        }

    }

    private fun updateValue(){
        binding.apply {
            tvTotalQuantity.text = viewModel.totalQuantity.toString()
            tvTotalPrice.text = viewModel.totalPrice.toString()
        }
    }


}