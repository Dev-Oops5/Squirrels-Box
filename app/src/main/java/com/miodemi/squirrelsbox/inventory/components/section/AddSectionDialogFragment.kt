package com.miodemi.squirrelsbox.inventory.components.section

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.inventory.data.SectionData
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddSectionBinding
import java.util.*

class AddSectionDialogFragment : DialogFragment() {

    //Realtime database
    private lateinit var database: DatabaseReference

    //binding
    internal lateinit var binding: FragmentDialogAddSectionBinding

    private var id = ""
    private var name = ""
    private var dateCreated = ""
    private var color = ""
    private var favourite = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogAddSectionBinding.inflate(layoutInflater)
        val view : View = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //init realtime database
        database = FirebaseDatabase.getInstance().getReference("boxes")

        binding.addSectionBtn.setOnClickListener{
            storeData()
            Toast.makeText(this.activity,"Section added successfully :)", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }


        // Inflate the layout for this fragment
        return view
    }

    private fun storeData() {
        id = UUID.randomUUID().toString();
        name = binding.sectionNameEt.text.toString().trim()

        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = sdf.format(Date())
        dateCreated = currentDate

        //missing color
        //missing favourite

        val sectionData = SectionData(id,name,dateCreated,color, favourite)
        database.child(id).setValue(sectionData)
    }
}