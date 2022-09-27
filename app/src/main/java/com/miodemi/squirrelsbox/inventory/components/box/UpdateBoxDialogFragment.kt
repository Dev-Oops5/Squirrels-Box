package com.miodemi.squirrelsbox.inventory.components.box

import android.app.ProgressDialog.show
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentDialogUpdateBoxBinding
import com.miodemi.squirrelsbox.inventory.navigation.homebox.HomeBoxRepository
import com.miodemi.squirrelsbox.inventory.navigation.homebox.HomeBoxViewModel

class UpdateBoxDialogFragment : DialogFragment() {

    //binding
    internal lateinit var binding: FragmentDialogUpdateBoxBinding

    private val repository = UpdateBoxDialogRepository()

    private val sharedViewModel : SharedBoxDialogModelViewFragment by activityViewModels()

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
        val inputId = args?.get("currBoxId").toString()
        val inputName = args?.get("currBoxName").toString()

        binding.titleTv.text = inputName

//        sharedViewModel.id.observe(viewLifecycleOwner) { id ->
//            binding.titleTv.text = id
//        }

        binding.updateBoxBtn.setOnClickListener {
            repository.updateData(inputId,binding.boxNameEt.text.toString())
            dialog!!.dismiss()
        }

        binding.deleteBoxIc.setOnClickListener{
            val deleteBoxDialogFragment = DeleteBoxDialogFragment()
            val bundle = Bundle()
            bundle.putString("currBoxId", inputId)
            bundle.putString("currBoxName", inputName)
            deleteBoxDialogFragment.arguments = bundle

            requireActivity().supportFragmentManager.beginTransaction()
                .add(deleteBoxDialogFragment, "deleteBoxDialog")
                .commit()
            dialog?.dismiss()
        }

        binding.cancelBoxBtn.setOnClickListener {
            dialog?.dismiss()
        }

        // Inflate the layout for this fragment
        return view
    }

}