package com.miodemi.squirrelsbox.dialogs.box

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddBoxBinding

class AddBoxDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogAddBoxBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //init data binding in a fragment
        binding = FragmentDialogAddBoxBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //--Insert actions here--//


        // Inflate the layout for this fragment
        return view
    }
}