package com.android.pizzaapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.android.pizzaapp.R
import com.android.pizzaapp.data.remote.NetworkHandler
import com.android.pizzaapp.databinding.ActivityMainBinding
import com.android.pizzaapp.ui.viewModel.AppNavigator
import com.android.pizzaapp.ui.viewModel.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(),AppNavigator {
   private val viewModel : AppViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.setNavigator(this)
        viewModel.invoke()
        changeFragment(PizzaFragment())


    }

    fun changeFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().add(R.id.container,fragment).commit()
    }





}