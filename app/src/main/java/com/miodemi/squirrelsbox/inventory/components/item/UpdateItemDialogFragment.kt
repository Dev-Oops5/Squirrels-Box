package com.miodemi.squirrelsbox.inventory.components.item

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
import com.miodemi.squirrelsbox.databinding.FragmentDialogUpdateItemBinding
import com.miodemi.squirrelsbox.inventory.components.section.DeleteSectionDialogFragment
import com.miodemi.squirrelsbox.inventory.components.section.SectionDialogViewModel

class UpdateItemDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogUpdateItemBinding

    private val viewModel : ItemDialogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogUpdateItemBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        viewModel.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        viewModel.sectionName.observe(viewLifecycleOwner) {
            binding.boxNameTv.text = it
        }

        binding.updateItemBtn.setOnClickListener {
            viewModel.updateFastData(
                binding.itemNameEt.text.toString(),
                binding.colorEt.text.toString(),
                binding.amountEt.text.toString().toInt()
            )
            dialog!!.dismiss()
        }

        binding.deleteItemIc.setOnClickListener{
            activity?.let { it -> DeleteItemDialogFragment().show(it.supportFragmentManager, "DeleteItemDialog") }
            dialog?.dismiss()
        }

        binding.cancelItemBtn.setOnClickListener {
            dialog?.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}