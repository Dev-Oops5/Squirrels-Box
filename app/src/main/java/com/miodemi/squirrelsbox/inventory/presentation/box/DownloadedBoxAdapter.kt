package com.miodemi.squirrelsbox.inventory.presentation.box

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.HomeViewModel
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.ItemBoxBinding
import com.miodemi.squirrelsbox.inventory.application.box.BoxDialogViewModel
import com.miodemi.squirrelsbox.inventory.application.section.DownloadedSectionViewModel
import com.miodemi.squirrelsbox.inventory.application.section.HomeSectionViewModel
import com.miodemi.squirrelsbox.inventory.application.section.SectionDialogViewModel
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.profile.presentation.download.DownloadedSectionFragment
import com.miodemi.squirrelsbox.profile.presentation.home.HomeSectionFragment

class DownloadedBoxAdapter () : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val downloadedBoxItems = mutableListOf<BoxData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DownloadedBoxItemViewHolder(parent)
    }

    inner class DownloadedBoxItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).
        inflate(R.layout.item_box, parent, false)
    ) {

        private val binding = ItemBoxBinding.bind(itemView)

        fun onBind(
            downloadedBoxItem : BoxData
        ){
            binding.boxNameTv.text = downloadedBoxItem.name.toString()
            val boxId = downloadedBoxItem.id.toString()
            val boxName = downloadedBoxItem.name.toString()
            val boxDateCreated = downloadedBoxItem.dateCreated.toString()
            binding.editBtn.setOnClickListener { v : View ->
                val activity = v.context as AppCompatActivity
                val fragmentViewFragment : BoxDialogViewModel by activity.viewModels()
                fragmentViewFragment.setId(boxId)
                fragmentViewFragment.setName(boxName)
                fragmentViewFragment.setDate(boxDateCreated)

                UpdateBoxDialogFragment().show(activity.supportFragmentManager, "UpdateBoxDialog")
            }
            binding.boxCV.setOnClickListener{v : View ->
                val activity = v.context as AppCompatActivity
                val downloadedSectionViewModel : DownloadedSectionViewModel by activity.viewModels()
                val sectionDialogViewModel : SectionDialogViewModel by activity.viewModels()

                downloadedSectionViewModel.setBoxId(boxId)
                downloadedSectionViewModel.setName(boxName)

                sectionDialogViewModel.setBoxId(boxId)
                sectionDialogViewModel.setBoxName(boxName)

                //Change
                val downloadedViewModel : HomeViewModel by activity.viewModels()
                downloadedViewModel.setId(2)

                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()

                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.contentLy, DownloadedSectionFragment()).addToBackStack(null)
                    .commit()
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as DownloadedBoxItemViewHolder).onBind(
            downloadedBoxItem = downloadedBoxItems[position])
    }

    override fun getItemCount(): Int = downloadedBoxItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setDownloadedBoxItems(newsFeedItems: List<BoxData>?) {
        this.downloadedBoxItems.clear()
        this.downloadedBoxItems.addAll(newsFeedItems ?: emptyList())
        notifyDataSetChanged()
    }

}