package com.keshav.photoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


internal class SingleItemAdapter(private var itemsList: List<Model>) :
    RecyclerView.Adapter<SingleItemAdapter.MyViewHolder>() {
    private val TAG = "FirstFragment"

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        val myAdapter = SingleItemDetailsAdapterAdapter(item.imageUrl)
        val myLayoutManager = GridLayoutManager(holder.recyclerView.context,item.imageUrl.size)
        holder.recyclerView.apply {
            layoutManager = myLayoutManager
            adapter = myAdapter
        }

    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${itemsList.size}")
        return itemsList.size
    }
}