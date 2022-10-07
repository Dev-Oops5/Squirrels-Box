package com.miodemi.squirrelsbox.inventory.components.box

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.inventory.data.BoxData
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddBoxBinding
import java.util.*

class AddBoxDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogAddBoxBinding

    private val viewModel : BoxDialogModelViewFragment by activityViewModels()

    private var name = ""
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


        binding.addBoxBtn.setOnClickListener{
            name = binding.boxNameEt.text.toString().trim()
            boxType = binding.activeRb.isChecked

            viewModel.storeData(name, boxType, privateLink, download, favourite)
            Toast.makeText(this.activity,"Box added successfully :)", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }
}