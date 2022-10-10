package com.miodemi.squirrelsbox.inventory.navigation.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.HomeViewModel
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.ItemSectionBinding
import com.miodemi.squirrelsbox.inventory.components.item.ItemDialogViewModel
import com.miodemi.squirrelsbox.inventory.components.section.SectionDialogViewModel
import com.miodemi.squirrelsbox.inventory.components.section.UpdateSectionDialogFragment
import com.miodemi.squirrelsbox.inventory.data.SectionData
import com.miodemi.squirrelsbox.profile.navigation.home.HomeItemFragment
import com.miodemi.squirrelsbox.profile.navigation.home.HomeSectionFragment

class HomeSectionAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val homeSectionItems = mutableListOf<SectionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SectionItemViewHolder(parent)
    }

    inner class SectionItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_section,parent, false)
    ){
        private val binding = ItemSectionBinding.bind(itemView)

        fun onBind(
            homeSectionItem : SectionData
        ){
            binding.sectionNameTv.text = homeSectionItem.name.toString()
            val sectionId = homeSectionItem.id.toString()
            val boxId = homeSectionItem.boxId.toString()
            val sectionName = homeSectionItem.name.toString()
            val sectionDateCreated = homeSectionItem.dateCreated.toString()

            binding.editBtn.setOnClickListener { v : View ->
                val activity = v.context as AppCompatActivity
                val fragmentViewFragment : SectionDialogViewModel by activity.viewModels()
                fragmentViewFragment.setId(sectionId)
                fragmentViewFragment.setBoxId(boxId)
                fragmentViewFragment.setName(sectionName)

                UpdateSectionDialogFragment().show(activity.supportFragmentManager, "UpdateSectionDialog")
            }

            binding.sectionCV.setOnClickListener { v : View ->
                val activity = v.context as AppCompatActivity
                val homeItemViewModel : HomeItemViewModel by activity.viewModels()
                val itemDialogViewModel : ItemDialogViewModel by activity.viewModels()

                homeItemViewModel.setBoxId(boxId)
                itemDialogViewModel.setBoxId(boxId)

                homeItemViewModel.setSectionId(sectionId)
                itemDialogViewModel.setSectionId(sectionId)

                homeItemViewModel.setSectionName(sectionName)
                itemDialogViewModel.setSectionName(sectionName)

                val homeViewModel : HomeViewModel by activity.viewModels()
                homeViewModel.setId(3)

                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.contentLy, HomeItemFragment()).addToBackStack(null)
                    .commit()
            }

        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeSectionAdapter.SectionItemViewHolder).onBind(
            homeSectionItem = homeSectionItems[position])
    }

    override fun getItemCount(): Int = homeSectionItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeSectionItems(newsFeedItems: List<SectionData>?) {
        this.homeSectionItems.clear()
        this.homeSectionItems.addAll(newsFeedItems ?: emptyList())
        notifyDataSetChanged()
    }
}