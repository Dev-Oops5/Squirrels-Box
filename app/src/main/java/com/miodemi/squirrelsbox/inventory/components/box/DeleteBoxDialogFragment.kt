package com.miodemi.squirrelsbox.inventory.components.box

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentDialogDeleteBoxBinding

class DeleteBoxDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogDeleteBoxBinding

    private val repository = DeleteBoxDialogRepository()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogDeleteBoxBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        val args = this.arguments
        val inputId = args?.get("currBoxId").toString()
        val inputName = args?.get("currBoxName").toString()

        binding.boxTv.text = inputName

        binding.deleteBoxBtn.setOnClickListener {
            repository.deleteData(inputId)
            dialog?.dismiss()
        }

        binding.cancelBoxBtn.setOnClickListener {
            dialog?.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}