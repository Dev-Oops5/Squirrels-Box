package com.miodemi.squirrelsbox.inventory.presentation.item

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentDialogAddItemBinding
import com.miodemi.squirrelsbox.inventory.application.item.ItemDialogViewModel

class AddItemDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogAddItemBinding

    private val viewModel : ItemDialogViewModel by activityViewModels()

    private var name = ""
    private var color = ""
    private var description = ""
    private var amount = 0
    private val picture = ""
    private var favourite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogAddItemBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root


        viewModel.sectionName.observe(viewLifecycleOwner) {
            binding.boxNameTv.text = it
        }

        binding.addItemBtn.setOnClickListener{

            name = binding.itemNameEt.text.toString()
            color = binding.colorEt.text.toString()
            description = binding.descriptionEt.text.toString()
            amount = binding.amountEt.text.toString().toInt()

            viewModel.storeData(name,color,description,amount,picture,favourite)

            Toast.makeText(this.activity,"Item added successfully :)", Toast.LENGTH_SHORT).show()
            dialog!!.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}