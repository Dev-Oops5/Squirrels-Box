package com.miodemi.squirrelsbox.navigation.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.miodemi.squirrelsbox.databinding.FragmentMenuProfileBinding

class MenuProfileFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentMenuProfileBinding
    private var actBox = 5
    private var maxBox = 10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentMenuProfileBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        //assign current and max boxes amount in text view
        binding.actBoxTV.text = actBox.toString()
        binding.maxBoxTV.text = maxBox.toString()

        //assign  current and max boxes amount in progress bar
        binding.actBoxesPb.max = maxBox
        binding.actBoxesPb.progress = actBox

        // Inflate the layout for this fragment
        return view
    }

}