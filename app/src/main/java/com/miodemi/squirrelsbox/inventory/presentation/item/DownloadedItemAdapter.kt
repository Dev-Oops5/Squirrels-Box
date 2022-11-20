package com.miodemi.squirrelsbox.inventory.presentation.item

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.ItemObjectBinding
import com.miodemi.squirrelsbox.inventory.application.item.ItemDialogViewModel
import com.miodemi.squirrelsbox.inventory.domain.ItemData

class DownloadedItemAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val downloadedItemItems = mutableListOf<ItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemItemViewHolder(parent)
    }

    inner class ItemItemViewHolder (parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_object, parent, false)
    ){
        private val binding = ItemObjectBinding.bind(itemView)

        fun onBind(
            downloadedItemItem : ItemData
        ){
            // Image get
//            val newView : View = itemView
//            val activity1 = newView.context as AppCompatActivity
//            val fragmentViewFragment1 : ItemDialogViewModel by activity1.viewModels()
//            val fileName = homeItemItem.picture.toString()
//
//            val itemImage = fragmentViewFragment1.getImage(fileName)
//
//            binding.profileImage.setImageBitmap(itemImage)
            val fileName = downloadedItemItem.picture.toString()
            //val storageRef = FirebaseStorage.getInstance().reference.child("images/$fileName.jpg")

            /*val localfile = File.createTempFile("tempImage","jpg")
            storageRef.getFile(localfile)
                .addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                    Log.i("Yes", "Image $fileName retrieved")

                    binding.profileImage.setImageBitmap(bitmap)
                }
                .addOnFailureListener {  }

            binding.itemNameTv.text = homeItemItem.name.toString()*/

            val itemId = downloadedItemItem.id.toString()
            val sectionId = downloadedItemItem.sectionId.toString()
            val boxId = downloadedItemItem.boxId.toString()
            val sectionName = downloadedItemItem.name.toString()
            val sectionDateCreated = downloadedItemItem.dateCreated.toString()

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
        (holder as ItemItemViewHolder).onBind(
            downloadedItemItem = downloadedItemItems[position])
    }

    override fun getItemCount(): Int = downloadedItemItems.size

    @SuppressLint("NotifyDataSetChanged")
    fun setDownloadedItemItems(newsFeedItems: List<ItemData>?) {
        this.downloadedItemItems.clear()
        this.downloadedItemItems.addAll(newsFeedItems ?: emptyList())
        notifyDataSetChanged()
    }
}