package com.miodemi.squirrelsbox.profile.navigation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.miodemi.squirrelsbox.databinding.FragmentHomeSectionBinding
import com.miodemi.squirrelsbox.inventory.components.section.SectionDialogViewModel
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeBoxAdapter
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeBoxViewModel
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeSectionAdapter
import com.miodemi.squirrelsbox.inventory.navigation.home.HomeSectionViewModel

class HomeSectionFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeSectionBinding

    private val viewModel1 : HomeSectionViewModel by activityViewModels()

//    private val viewModel1: HomeSectionViewModel by lazy {
//        ViewModelProvider(this)[HomeSectionViewModel::class.java]
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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