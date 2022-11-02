package com.miodemi.squirrelsbox.inventory.navigation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.ItemObjectBinding
import com.miodemi.squirrelsbox.inventory.components.item.ItemDialogViewModel
import com.miodemi.squirrelsbox.inventory.components.item.UpdateItemDialogFragment
import com.miodemi.squirrelsbox.inventory.data.ItemData

class HomeItemAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val homeItemItems = mutableListOf<ItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemItemViewHolder(parent)
    }

    inner class ItemItemViewHolder (parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_object, parent, false)
    ){
        private val binding = ItemObjectBinding.bind(itemView)

        fun onBind(
            homeItemItem : ItemData
        ){
            binding.itemNameTv.text = homeItemItem.name.toString()
            val itemId = homeItemItem.id.toString()
            val sectionId = homeItemItem.sectionId.toString()
            val boxId = homeItemItem.boxId.toString()
            val sectionName = homeItemItem.name.toString()
            val sectionDateCreated = homeItemItem.dateCreated.toString()

            binding.editBtn.setOnClickListener { v : View ->
                val activity = v.context as AppCompatActivity
                val fragmentViewFragment : ItemDialogViewModel by activity.viewModels()
                fragmentViewFragment.setId(itemId)
                fragmentViewFragment.setSectionId(sectionId)
                fragmentViewFragment.setBoxId(boxId)
                fragmentViewFragment.setName(sectionName)

                UpdateItemDialogFragment().show(activity.supportFragmentManager, "UpdateItemDialog")
            }

            
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeItemAdapter.ItemItemViewHolder).onBind(
            homeItemItem = homeItemItems[position])
    }

    override fun getItemCount(): Int = homeItemItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeItemItems(newsFeedItems: List<ItemData>?) {
        this.homeItemItems.clear()
        this.homeItemItems.addAll(newsFeedItems ?: emptyList())
        notifyDataSetChanged()
    }
}