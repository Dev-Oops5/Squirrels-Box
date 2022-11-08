package com.miodemi.squirrelsbox.inventory.presentation.item

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddItemBinding
import com.miodemi.squirrelsbox.inventory.application.item.ItemDialogViewModel

class AddItemDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogAddItemBinding

    private val viewModel : ItemDialogViewModel by activityViewModels()

    lateinit var ImageUri : Uri

    private var name = ""
    private var color = ""
    private var description = ""
    private var amount = 0
    private val picture = ""
    private var favourite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogAddItemBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        viewModel.sectionName.observe(viewLifecycleOwner) {
            binding.boxNameTv.text = it
        }

        binding.addItemBtn.setOnClickListener{

            name = binding.itemNameEt.text.toString()
            color = binding.colorEt.text.toString()
            description = binding.descriptionEt.text.toString()
            amount = binding.amountEt.text.toString().toInt()


            viewModel.storeData(name,color,description,amount,picture,favourite)

            Toast.makeText(this.activity,"Item added successfully :)", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }

        binding.cameraIB.setOnClickListener {
            selectImage()
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun selectImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK){
            ImageUri = data?.data!!
            binding.pictureIV.setImageURI(ImageUri)
            viewModel.setPicture(ImageUri)
            viewModel.storePicture(ImageUri)
        }
    }


}