package com.miodemi.squirrelsbox.detail.homebox

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.data.models.BoxData
import com.miodemi.squirrelsbox.databinding.ItemBoxBinding
import java.lang.ref.WeakReference

class HomeBoxAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val homeBoxItems = mutableListOf<BoxData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeBoxItemViewHolder(parent)
    }

    inner class HomeBoxItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).
                inflate(R.layout.item_box, parent, false)
    ) {

        private val binding = ItemBoxBinding.bind(itemView)

        fun onBind(
            homeBoxItem : BoxData
        ){
            binding.boxNameTv.text = homeBoxItem.name.toString()
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeBoxItemViewHolder).onBind(
            homeBoxItem = homeBoxItems[position]
        )
    }

    override fun getItemCount(): Int = homeBoxItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(newsFeedItems: List<BoxData>?) {
        this.homeBoxItems.clear()
        this.homeBoxItems.addAll(newsFeedItems ?: emptyList())
        notifyDataSetChanged()
    }
}