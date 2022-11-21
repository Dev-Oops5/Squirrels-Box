package com.miodemi.squirrelsbox.inventory.presentation.section

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
import com.miodemi.squirrelsbox.inventory.application.item.DownloadedItemViewModel
import com.miodemi.squirrelsbox.inventory.application.item.ItemDialogViewModel
import com.miodemi.squirrelsbox.inventory.application.section.SectionDialogViewModel
import com.miodemi.squirrelsbox.inventory.domain.SectionData
import com.miodemi.squirrelsbox.profile.presentation.download.DownloadedItemFragment

class DownloadedSectionAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val downloadedSectionItems = mutableListOf<SectionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SectionItemViewHolder(parent)
    }

    inner class SectionItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_section,parent, false)
    ){
        private val binding = ItemSectionBinding.bind(itemView)

        fun onBind(
            downloadedSectionItem : SectionData
        ){
            binding.sectionNameTv.text = downloadedSectionItem.name.toString()
            val sectionId = downloadedSectionItem.id.toString()
            val boxId = downloadedSectionItem.boxId.toString()
            val sectionName = downloadedSectionItem.name.toString()

            //val sectionDateCreated = downloadedSectionItem.dateCreated.toString()

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
                val downloadedItemViewModel : DownloadedItemViewModel by activity.viewModels()
                val itemDialogViewModel : ItemDialogViewModel by activity.viewModels()

                downloadedItemViewModel.setBoxId(boxId)
                itemDialogViewModel.setBoxId(boxId)

                downloadedItemViewModel.setSectionId(sectionId)
                itemDialogViewModel.setSectionId(sectionId)

                downloadedItemViewModel.setSectionName(sectionName)
                itemDialogViewModel.setSectionName(sectionName)

                val homeViewModel : HomeViewModel by activity.viewModels()
                homeViewModel.setId(3)

                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.contentLy, DownloadedItemFragment()).addToBackStack(null)
                    .commit()
            }

        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as SectionItemViewHolder).onBind(
            downloadedSectionItem = downloadedSectionItems[position])
    }

    override fun getItemCount(): Int = downloadedSectionItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setDownloadedSectionItems(newsFeedItems: List<SectionData>?) {
        this.downloadedSectionItems.clear()
        this.downloadedSectionItems.addAll(newsFeedItems ?: emptyList())
        notifyDataSetChanged()
    }
}