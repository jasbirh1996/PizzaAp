package com.android.pizzaapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.android.pizzaapp.R
import com.android.pizzaapp.data.remote.NetworkHandler
import com.android.pizzaapp.data.remote.model.SelectedItem
import com.android.pizzaapp.databinding.FragmentPizzaBinding
import com.android.pizzaapp.ui.viewModel.AppNavigator
import com.android.pizzaapp.ui.viewModel.AppViewModel
import com.android.pizzaapp.util.AddToCartListener
import com.android.pizzaapp.util.CustomizePizzaDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PizzaFragment : Fragment() {

    private lateinit var binding: FragmentPizzaBinding
    val viewModel : AppViewModel by activityViewModels ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPizzaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        setListener()

    }

    private fun observer() {
        viewModel.pizzaLiveData.observe(requireActivity()) {
            when (it) {
                is NetworkHandler.Success -> {
                    // success data is here
                    Log.d("status","success")
                    binding.data = it.data
                    viewModel.pizzaData = it.data

                }
                is NetworkHandler.Error -> {
                    // error
                    Log.e("error", it.exception.toString())


                }
            }
        }
    }

    private fun setListener() {
        binding.btAdd.setOnClickListener {
            customizePizza()
        }
    }

    private fun customizePizza() {
        viewModel.pizzaData?.let {
            CustomizePizzaDialog(requireContext(),it.crusts,it.defaultCrust, object : AddToCartListener {
                override fun addToCart(selectedItem: SelectedItem?) {
                    viewModel.getNavigator()?.selectedItemList(selectedItem)
                    viewModel.getNavigator()?.switchFragment()
                }


            }).show()
        }

    }


}