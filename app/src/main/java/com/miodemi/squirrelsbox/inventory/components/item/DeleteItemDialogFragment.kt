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
import com.miodemi.squirrelsbox.databinding.FragmentDialogDeleteItemBinding
import com.miodemi.squirrelsbox.inventory.components.section.SectionDialogViewModel

class DeleteItemDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogDeleteItemBinding

    private val viewModel : ItemDialogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogDeleteItemBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        viewModel.name.observe(viewLifecycleOwner){
            binding.itemTv.text = it
        }

        binding.deleteItemBtn.setOnClickListener {
            viewModel.deleteData()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}