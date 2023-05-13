package com.android.pizzaapp.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.pizzaapp.data.remote.model.PizzaAppResponse
import com.android.pizzaapp.data.remote.model.SelectedItem
import com.android.pizzaapp.databinding.FragmentPizzaBinding
import com.android.pizzaapp.databinding.ItemCrustBinding
import com.android.pizzaapp.util.RemoveItemFromCart
import java.lang.ref.WeakReference

class CartAdapter : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var selectedItemList : MutableList<SelectedItem> =  mutableListOf()
    private  lateinit var listener : AdapterRemoveItemListener
    inner class MyViewHolder(var binding: ItemCrustBinding) : RecyclerView.ViewHolder(binding.root){

        fun onBind(pos : Int){
            binding.data = selectedItemList?.get(pos)
            binding.btRemoveCart.setOnClickListener {
                listener.removeItem(selectedItemList?.get(pos),pos)
            }
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
     return MyViewHolder(ItemCrustBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
      return selectedItemList?.size!!
    }

    fun setSelectedItemList(list : MutableList<SelectedItem>){
        this.selectedItemList = list
    }
    fun cartListener(mListener : AdapterRemoveItemListener){
        this.listener = mListener
    }


    interface AdapterRemoveItemListener{
        fun removeItem(selectedItem: SelectedItem?,pos : Int)
    }



}