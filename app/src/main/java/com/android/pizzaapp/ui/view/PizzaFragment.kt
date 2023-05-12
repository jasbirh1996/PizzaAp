package com.android.pizzaapp.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.android.pizzaapp.R
import com.android.pizzaapp.data.remote.NetworkHandler
import com.android.pizzaapp.databinding.FragmentPizzaBinding
import com.android.pizzaapp.ui.viewModel.AppViewModel

class PizzaFragment : Fragment() {

    private lateinit var binding : FragmentPizzaBinding
    val viewModel : AppViewModel by activityViewModels ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPizzaBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer(){
        viewModel.pizzaLiveData.observe(requireActivity()){
            when(it){
                is NetworkHandler.Success ->{
                // success data is here
                    binding.data = it.data

                }
                is NetworkHandler.Error->{
                    // error
                    Log.e("error",it.exception.toString())


                }
            }
        }
    }


}