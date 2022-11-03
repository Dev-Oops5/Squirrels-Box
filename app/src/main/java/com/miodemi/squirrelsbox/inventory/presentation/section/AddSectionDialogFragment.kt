package com.miodemi.squirrelsbox.inventory.presentation.section

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddSectionBinding
import com.miodemi.squirrelsbox.inventory.application.section.SectionDialogViewModel

class AddSectionDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogAddSectionBinding

    private val viewModel : SectionDialogViewModel by activityViewModels()

    private var name = ""
    private var color = ""
    private var favourite = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogAddSectionBinding.inflate(layoutInflater)
        val view : View = binding.root
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        viewModel.boxName.observe(viewLifecycleOwner) {
            binding.boxNameTv.text = it
        }

        binding.addSectionBtn.setOnClickListener{

            name = binding.sectionNameEt.text.toString()
            color = binding.colorEt.text.toString()

            viewModel.storeData(name, color, favourite)

            Toast.makeText(this.activity,"Section added successfully :)", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }
}