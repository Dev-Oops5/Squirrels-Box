package com.miodemi.squirrelsbox.profile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentRegisterUserInfoBinding

class RegisterUserInfoFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentRegisterUserInfoBinding

    //connected fragments
    private val userMobileFragment = RegisterMobileFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentRegisterUserInfoBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        binding.registerContinueBtn.setOnClickListener { v: View ->
            val activity = v.context as AppCompatActivity
            //declare instance you want to replace
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.registerFLy, userMobileFragment).addToBackStack(null)
                .commit()

//            var fr = fragmentManager?.beginTransaction()
//            fr?.replace(R.id.registerFLy, userMobileFragment)
//            fr?.commit()
        }

        // Inflate the layout for this fragment
        return view
    }

}