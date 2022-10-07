package com.miodemi.squirrelsbox.inventory.components.box

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentDialogDeleteBoxBinding

class DeleteBoxDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogDeleteBoxBinding

    private val viewModel : BoxDialogModelViewFragment by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogDeleteBoxBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        binding.deleteBoxBtn.setOnClickListener {
            viewModel.deleteData()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}