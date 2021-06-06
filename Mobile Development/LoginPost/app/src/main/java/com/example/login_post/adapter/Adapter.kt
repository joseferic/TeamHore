package com.example.login_post.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_post.R
import com.example.login_post.model.CompDatas
import kotlinx.android.synthetic.main.row_layout.view.*

class Adapter: RecyclerView.Adapter<Adapter.MyViewHolder>() {

    private var myList =  emptyList<CompDatas>()

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false))
    }


    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.id_foodData.text = myList[position].id_foodData.toString()
        holder.itemView.id_compsData.text = myList[position].id_compsData.toString()
        holder.itemView.name_compsData.text = myList[position].name_compsData
        holder.itemView.desc_compsData.text = myList[position].desc_compsData
    }

    fun setData(newList: List<CompDatas>){
        myList = newList
        notifyDataSetChanged()
    }

}