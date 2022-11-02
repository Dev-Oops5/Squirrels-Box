package com.miodemi.squirrelsbox.inventory.presentation.section

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentDialogUpdateSectionBinding
import com.miodemi.squirrelsbox.inventory.application.section.SectionDialogViewModel

class UpdateSectionDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogUpdateSectionBinding

    private val viewModel : SectionDialogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogUpdateSectionBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        viewModel.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        viewModel.boxName.observe(viewLifecycleOwner) {
            binding.boxNameTv.text = it
        }

        binding.updateSectionBtn.setOnClickListener {
            viewModel.updateData(binding.sectionNameEt.text.toString(), binding.colorEt.text.toString())
            dialog!!.dismiss()
        }

        binding.deleteSectionIc.setOnClickListener{
            activity?.let { it -> DeleteSectionDialogFragment().show(it.supportFragmentManager, "DeleteBoxDialog") }
            dialog?.dismiss()
        }

        binding.cancelSectionBtn.setOnClickListener {
            dialog?.dismiss()
        }
        // Inflate the layout for this fragment
        return view
    }
}