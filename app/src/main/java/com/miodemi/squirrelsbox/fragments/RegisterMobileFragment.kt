package com.miodemi.squirrelsbox.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.miodemi.squirrelsbox.databinding.FragmentRegisterMobileBinding
import kotlinx.android.synthetic.main.fragment_register_mobile.*


class RegisterMobileFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentRegisterMobileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentRegisterMobileBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        //Edit text hop
        binding.code1Et.addTextChangedListener {
            if (binding.code1Et.text.toString().length == 1)
                binding.code2Et.requestFocus()
        }
        binding.code2Et.addTextChangedListener {
            if (binding.code2Et.text.toString().length == 1)
                binding.code3Et.requestFocus()
        }
        binding.code3Et.addTextChangedListener {
            if (binding.code3Et.text.toString().length == 1)
                binding.code4Et.requestFocus()
        }
        binding.code4Et.addTextChangedListener {
            if (binding.code4Et.text.toString().length == 1)
                binding.code5Et.requestFocus()
        }
        binding.code5Et.addTextChangedListener {
            if (binding.code5Et.text.toString().length == 1)
                binding.code6Et.requestFocus()
        }
        //Sending action to confirm code
        binding.code6Et.addTextChangedListener {
            if (binding.code6Et.text.toString().length == 1){
//                binding.code1Et.text.clear()
//                binding.code2Et.text.clear()
//                binding.code3Et.text.clear()
//                binding.code4Et.text.clear()
//                binding.code5Et.text.clear()
//                binding.code6Et.text.clear()
                Toast.makeText(this.activity , "Automatically sent", Toast.LENGTH_SHORT).show()
            }

        }

        // Inflate the layout for this fragment
        return view
    }

}