package com.keshav.photoapp

import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

internal class SingleItemDetailsAdapterAdapter(private var itemsList: List<Uri>) :
    RecyclerView.Adapter<SingleItemDetailsAdapterAdapter.MyViewHolder>() {
    private  val TAG = "FirstFragment"
    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item_details, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.imageView.setImageURI(item)

    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${itemsList.size}")
        return itemsList.size
    }
}