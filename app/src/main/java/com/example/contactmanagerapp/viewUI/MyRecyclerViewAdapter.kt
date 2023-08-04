package com.example.contactmanagerapp.viewUI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.contactmanagerapp.R
import com.example.contactmanagerapp.databinding.CardItemBinding
import com.example.contactmanagerapp.room.Users

class MyRecyclerViewAdapter (private val userLists:List<Users>
,private val clickListener: (Users)->Unit) :RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>(){

    inner class MyViewHolder(val binding:CardItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind( user:Users,clickListener: (Users) -> Unit){
            binding.nameTextView.text = user.name
            binding.emailTextView.text = user.email
            binding.listItemLayout.setOnClickListener(
                {
                    clickListener(user)
                }
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
       val layoutInflater = LayoutInflater.from(parent.context)
        val binding:CardItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return userLists.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(userLists[position],clickListener)
    }

}