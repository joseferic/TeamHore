package com.bangkitsubmission.pastiin_ui.Adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bangkitsubmission.pastiin_ui.R
import com.bangkitsubmission.pastiin_ui.databinding.RowItemGlosariumBinding
import com.bangkitsubmission.pastiin_ui.model.FruitDatas
import com.bumptech.glide.Glide


class AdapterGlorasium : RecyclerView.Adapter<AdapterGlorasium.MyViewHolder>(){
    private var listFruit = ArrayList<FruitDatas>()

    fun setFruit(movies: List<FruitDatas>?) {
        if (movies == null) return
        this.listFruit.clear()
        this.listFruit.addAll(movies)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemsMoviesBinding = RowItemGlosariumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemsMoviesBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fruit = listFruit[position]
        holder.bind(fruit)
    }

    override fun getItemCount(): Int = listFruit.size

    class MyViewHolder(private val binding: RowItemGlosariumBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fruitDatas: FruitDatas) {
            with(binding) {
                tvFruitName.text = fruitDatas.name_Fruit
                Glide.with(itemView.context)
                    .load(fruitDatas.pict_Fruit)
                    .into(ivItemPhotoBuahGlosariom)

                itemView.setOnClickListener {
                    //val intent = Intent(itemView.context, DetailActivity::class.java)
                    //intent.putExtra(DetailActivity.EXTRA_MOVIES, movie)
                    //itemView.context.startActivity(intent)
                    Log.d("Recycler View dipencet", "${fruitDatas.name_Fruit}")
                }
            }
        }
    }
}