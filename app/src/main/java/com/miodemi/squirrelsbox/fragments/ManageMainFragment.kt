package com.miodemi.squirrelsbox.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentManageMainBinding

class ManageMainFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentManageMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentManageMainBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        //--Insert actions here--//

        // Inflate the layout for this fragment
        return view
    }

}