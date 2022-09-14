package com.miodemi.squirrelsbox.dialogs.box

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.data.BoxData
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddBoxBinding
import java.util.*

class AddBoxDialogFragment : DialogFragment() {

    //Realtime database
    private lateinit var database: DatabaseReference

    //binding
    internal lateinit var binding: FragmentDialogAddBoxBinding

    private var id = ""
    private var name = ""
    private var dateCreated = ""
    private var boxType = true
    private var privateLink = ""
    private var download = true
    private var favourite = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init data binding in a fragment
        binding = FragmentDialogAddBoxBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //init realtime database
        database = FirebaseDatabase.getInstance().getReference("boxes")

        binding.addBoxBtn.setOnClickListener{
            storeData()
            Toast.makeText(this.activity,"Box added successfully :)", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun storeData() {
        id = UUID.randomUUID().toString();
        name = binding.boxNameEt.text.toString().trim()
        boxType = binding.activeRb.isChecked

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        dateCreated = currentDate

        //missing private link
        //missing download
        //missing favourite

        val boxData = BoxData(id,name,dateCreated,boxType,privateLink,download,favourite)
        database.child(id).setValue(boxData)
    }
}