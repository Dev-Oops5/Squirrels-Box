package com.miodemi.squirrelsbox.dialogs.item

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddItemBinding

class AddItemDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogAddItemBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogAddItemBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        //--Insert actions here--//

        // Inflate the layout for this fragment
        return view
    }

}