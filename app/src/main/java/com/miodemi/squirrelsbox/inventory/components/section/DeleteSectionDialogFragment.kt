package com.miodemi.squirrelsbox.inventory.components.section

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentDialogDeleteSectionBinding
import com.miodemi.squirrelsbox.inventory.components.box.BoxDialogViewModel

class DeleteSectionDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogDeleteSectionBinding

    private val viewModel : SectionDialogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogDeleteSectionBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        viewModel.name.observe(viewLifecycleOwner){
            binding.sectionTv.text = it
        }

        binding.deleteSectionBtn.setOnClickListener {
            viewModel.deleteData()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}