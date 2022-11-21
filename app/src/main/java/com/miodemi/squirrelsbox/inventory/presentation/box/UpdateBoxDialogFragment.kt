package com.miodemi.squirrelsbox.inventory.presentation.box

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.miodemi.squirrelsbox.databinding.FragmentDialogUpdateBoxBinding
import com.miodemi.squirrelsbox.inventory.application.box.BoxDialogViewModel

class UpdateBoxDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogUpdateBoxBinding

    private val viewModel : BoxDialogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentDialogUpdateBoxBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //this value must be returned
        val view : View = binding.root

        viewModel.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        binding.updateBoxBtn.setOnClickListener {
            viewModel.updateData(binding.boxNameEt.text.toString())
            dialog!!.dismiss()
        }

        binding.deleteBoxIc.setOnClickListener{
            activity?.let { it -> DeleteBoxDialogFragment().show(it.supportFragmentManager, "DeleteBoxDialog") }
            dialog?.dismiss()
        }

        binding.cancelBoxBtn.setOnClickListener {
            dialog?.dismiss()
        }

        binding.shareBoxTv.setOnClickListener{
            lifecycleScope.launchWhenStarted {
                this@UpdateBoxDialogFragment.context?.let { it1 -> viewModel.downloadBox(it1) }
                Toast.makeText(this@UpdateBoxDialogFragment.activity, viewModel.result.value, Toast.LENGTH_LONG).show()
            }
        }
        binding.exportBoxTv.setOnClickListener{
            lifecycleScope.launchWhenStarted {
                this@UpdateBoxDialogFragment.context?.let { it1 -> viewModel.exportBox(it1) }
                Toast.makeText(this@UpdateBoxDialogFragment.activity, viewModel.result.value, Toast.LENGTH_LONG).show()
            }
        }

        // Inflate the layout for this fragment
        return view
    }
}