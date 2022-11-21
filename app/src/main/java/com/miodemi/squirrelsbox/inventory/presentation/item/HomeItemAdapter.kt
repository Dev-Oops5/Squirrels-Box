package com.miodemi.squirrelsbox.inventory.presentation.item

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Binder
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.FirebaseStorage
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.ItemObjectBinding
import com.miodemi.squirrelsbox.inventory.application.item.ItemDialogViewModel
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import kotlinx.android.synthetic.main.fragment_dialog_add_item.*
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File

class HomeItemAdapter(

) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val homeItemItems = mutableListOf<ItemData>()
    private lateinit var database: DatabaseReference

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
            // Image get
//            val newView : View = itemView
//            val activity1 = newView.context as AppCompatActivity
//            val fragmentViewFragment1 : ItemDialogViewModel by activity1.viewModels()
//            val fileName = homeItemItem.picture.toString()
//
//            val itemImage = fragmentViewFragment1.getImage(fileName)
//
//            binding.profileImage.setImageBitmap(itemImage)
            val fileName = homeItemItem.picture.toString()
            val storageRef = FirebaseStorage.getInstance().reference.child("images/$fileName.jpg")

            val localfile = File.createTempFile("tempImage","jpg")
            storageRef.getFile(localfile)
                .addOnSuccessListener {
                    val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
                    Log.i("Yes", "Image $fileName retrieved")

                    binding.profileImage.setImageBitmap(bitmap)
                }
                .addOnFailureListener {  }

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
        (holder as ItemItemViewHolder).onBind(
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