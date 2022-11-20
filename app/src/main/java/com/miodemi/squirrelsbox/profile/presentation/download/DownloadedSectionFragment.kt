package com.miodemi.squirrelsbox.profile.presentation.download

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.miodemi.squirrelsbox.databinding.FragmentDownloadedSectionBinding
import com.miodemi.squirrelsbox.inventory.application.section.DownloadedSectionViewModel
import com.miodemi.squirrelsbox.inventory.presentation.section.DownloadedSectionAdapter
import com.miodemi.squirrelsbox.inventory.presentation.section.HomeSectionAdapter
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab

class DownloadedSectionFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentDownloadedSectionBinding

    private val viewModel1 : DownloadedSectionViewModel by activityViewModels()
    private val viewModelFAB : AddDialogViewFab by activityViewModels()

    /*private val viewModel: DownloadedSectionViewModel by lazy {
        ViewModelProvider(this)[DownloadedSectionViewModel::class.java]
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFAB.setData("section")

        //init data binding in a fragment
        binding = FragmentDownloadedSectionBinding.inflate(layoutInflater)

        //this value must be returned
        val view : View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel1

        val itemSectionAdapter = DownloadedSectionAdapter()
        binding.allSectionsRv.adapter = itemSectionAdapter

        viewModel1.fetchNewsFeed(requireContext())

        viewModel1.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        // Inflate the layout for this fragment
        return view
    }
}