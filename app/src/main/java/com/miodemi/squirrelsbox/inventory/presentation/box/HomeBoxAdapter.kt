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
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.databinding.ItemBoxBinding
import com.miodemi.squirrelsbox.inventory.application.section.HomeSectionViewModel
import com.miodemi.squirrelsbox.inventory.application.box.BoxDialogViewModel
import com.miodemi.squirrelsbox.inventory.application.section.SectionDialogViewModel
import com.miodemi.squirrelsbox.profile.presentation.home.HomeSectionFragment

class HomeBoxAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
            val boxId = homeBoxItem.id.toString()
            val boxName = homeBoxItem.name.toString()
            val boxDateCreated = homeBoxItem.dateCreated.toString()
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
                val homeSectionViewModel : HomeSectionViewModel by activity.viewModels()
                val sectionDialogViewModel : SectionDialogViewModel by activity.viewModels()

                homeSectionViewModel.setBoxId(boxId)
                homeSectionViewModel.setName(boxName)

                sectionDialogViewModel.setBoxId(boxId)
                sectionDialogViewModel.setBoxName(boxName)

                //Change
                val homeViewModel : HomeViewModel by activity.viewModels()
                homeViewModel.setId(2)

                Toast.makeText(itemView.context, "You clicked on item # ${position + 1}", Toast.LENGTH_SHORT).show()

                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.contentLy, HomeSectionFragment()).addToBackStack(null)
                    .commit()
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as HomeBoxItemViewHolder).onBind(
            homeBoxItem = homeBoxItems[position])
    }

    override fun getItemCount(): Int = homeBoxItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setHomeBoxItems(newsFeedItems: List<BoxData>?) {
        this.homeBoxItems.clear()
        this.homeBoxItems.addAll(newsFeedItems ?: emptyList())
        notifyDataSetChanged()
    }
}