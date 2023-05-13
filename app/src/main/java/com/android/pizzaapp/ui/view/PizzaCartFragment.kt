package com.android.pizzaapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.android.pizzaapp.R
import com.android.pizzaapp.databinding.FragmentPizzaCartBinding
import com.android.pizzaapp.ui.viewModel.AppViewModel


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

    }


}