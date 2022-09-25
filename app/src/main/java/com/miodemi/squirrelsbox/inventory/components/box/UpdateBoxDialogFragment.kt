package com.miodemi.squirrelsbox.inventory.components.box

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentDialogUpdateBoxBinding
import com.miodemi.squirrelsbox.inventory.navigation.homebox.HomeBoxViewModel

class UpdateBoxDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogUpdateBoxBinding

    private val viewModel: UpdateBoxDialogModelViewFragment by lazy {
        ViewModelProvider(this)[UpdateBoxDialogModelViewFragment::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogUpdateBoxBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        val args = this.arguments
        val inputData = args?.get("currBoxId")
        binding.titleTv.text = inputData.toString()

        // Inflate the layout for this fragment
        return view
    }

}