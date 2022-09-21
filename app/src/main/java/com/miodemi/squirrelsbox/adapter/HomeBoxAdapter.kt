package com.miodemi.squirrelsbox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.data.BoxData

class HomeBoxAdapter (private val boxList: ArrayList<BoxData>) : RecyclerView.Adapter<HomeBoxAdapter.HomeBoxDataViewHolder>() {
    class HomeBoxDataViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val id:String? = null
        val name: TextView = itemView.findViewById(R.id.boxNameTv)
        val dateCreated:String? = null
        val boxType:Boolean? = null
        val privateLink: String? = null
        val download:Boolean? = null
        val favourite:Boolean? = null

        fun render(boxModel: BoxData) {
            name.text = boxModel.name.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeBoxDataViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_box,parent,false)
        return HomeBoxDataViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeBoxDataViewHolder, position: Int) {
        val currenIitem = boxList[position]
        holder.render(currenIitem)
    }

    override fun getItemCount(): Int = boxList.size

    fun addItem(i : Int, serviceData : BoxData) {
        boxList.add(i, serviceData)
        notifyDataSetChanged()
        notifyItemInserted(i)
    }
}