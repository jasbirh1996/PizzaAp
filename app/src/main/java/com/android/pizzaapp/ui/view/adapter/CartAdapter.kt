package com.android.pizzaapp.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pizzaapp.data.remote.model.PizzaAppResponse
import com.android.pizzaapp.databinding.FragmentPizzaBinding
import com.android.pizzaapp.databinding.ItemCrustBinding

class CartAdapter : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var crustList : ArrayList<PizzaAppResponse.Crust> =  arrayListOf()
    inner class MyViewHolder(var binding: ItemCrustBinding) : RecyclerView.ViewHolder(binding.root){

        fun onBind(pos : Int){
            binding.data = crustList.get(pos)
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     return MyViewHolder(ItemCrustBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
      return crustList.size
    }

    fun setCrustList(list : ArrayList<PizzaAppResponse.Crust>){
        this.crustList = list
    }
}