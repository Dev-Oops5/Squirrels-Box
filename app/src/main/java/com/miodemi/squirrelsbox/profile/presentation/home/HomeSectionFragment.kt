package com.miodemi.squirrelsbox.profile.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentHomeSectionBinding
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab
import com.miodemi.squirrelsbox.inventory.presentation.section.HomeSectionAdapter
import com.miodemi.squirrelsbox.inventory.application.section.HomeSectionViewModel

class HomeSectionFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeSectionBinding

    private val viewModel1 : HomeSectionViewModel by activityViewModels()
    private val viewModelFAB : AddDialogViewFab by activityViewModels()

//    private val viewModel1: HomeSectionViewModel by lazy {
//        ViewModelProvider(this)[HomeSectionViewModel::class.java]
//    }
    lateinit var _addview : AddDialogViewFab


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFAB.setData("section")
        //init data binding in a fragment
        binding = FragmentHomeSectionBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel1

        val itemSectionAdapter = HomeSectionAdapter()
        binding.allSectionsRv.adapter = itemSectionAdapter

        viewModel1.fetchNewsFeed()

        viewModel1.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        // Inflate the layout for this fragment
        return view
    }

}