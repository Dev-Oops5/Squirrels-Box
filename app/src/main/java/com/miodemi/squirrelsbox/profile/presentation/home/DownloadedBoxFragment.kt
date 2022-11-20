package com.miodemi.squirrelsbox.profile.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentDownloadedBoxBinding
import com.miodemi.squirrelsbox.inventory.application.box.DownloadedBoxViewModel
import com.miodemi.squirrelsbox.inventory.presentation.box.HomeBoxAdapter
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab

class DownloadedBoxFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentDownloadedBoxBinding

    private val viewModelFAB : AddDialogViewFab by activityViewModels()

    private val viewModel: DownloadedBoxViewModel by lazy {
        ViewModelProvider(this)[DownloadedBoxViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFAB.setData("box")

        //init data binding in a fragment
        binding = FragmentDownloadedBoxBinding.inflate(layoutInflater)

        //this value must be returned
        val view: View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val itemBoxAdapter = HomeBoxAdapter()
        binding.allBoxesRv.adapter = itemBoxAdapter

        viewModel.fetchNewsFeed(requireContext())

        // Inflate the layout for this fragment
        return view
    }

}