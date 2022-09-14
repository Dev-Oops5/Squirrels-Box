package com.miodemi.squirrelsbox.dialogs.item

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.data.ItemData
import com.miodemi.squirrelsbox.data.SectionData
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddItemBinding
import java.util.*

class AddItemDialogFragment : DialogFragment() {

    //Realtime database
    private lateinit var database: DatabaseReference

    //binding
    internal lateinit var binding: FragmentDialogAddItemBinding

    private var id = ""
    private var name = ""
    private var dateCreated = ""
    private var color = ""
    private var description = ""
    private var amount = 0
    private val picture = null
    private var favourite = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogAddItemBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        //init realtime database
        database = FirebaseDatabase.getInstance().getReference("boxes")

        binding.addItemBtn.setOnClickListener{
            storeData()
            Toast.makeText(this.activity,"Item added successfully :)", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun storeData() {
        id = UUID.randomUUID().toString();
        name = binding.itemNameTv.text.toString().trim()

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        dateCreated = currentDate

        //missing color
        description = binding.descriptionEt.text.toString().trim()
        amount = binding.amountEt.toString().toInt()
        //missing picture
        //missing favourite


        val itemData = ItemData(id,name,dateCreated,color, description,amount,picture,favourite)
        database.child(id).setValue(itemData)
    }

}