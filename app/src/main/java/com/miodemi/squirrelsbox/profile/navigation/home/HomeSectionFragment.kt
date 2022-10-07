package com.miodemi.squirrelsbox.profile.navigation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentHomeSectionBinding
import com.miodemi.squirrelsbox.inventory.navigation.homebox.HomeSectionViewModel

class HomeSectionFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeSectionBinding

    private val viewModel : HomeSectionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentHomeSectionBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        viewModel.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        // Inflate the layout for this fragment
        return view
    }

}