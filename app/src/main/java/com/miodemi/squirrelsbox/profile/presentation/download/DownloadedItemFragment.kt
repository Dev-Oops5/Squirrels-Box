package com.miodemi.squirrelsbox.profile.presentation.download

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentDownloadedItemBinding
import com.miodemi.squirrelsbox.inventory.application.item.DownloadedItemViewModel
import com.miodemi.squirrelsbox.inventory.presentation.item.DownloadedItemAdapter
import com.miodemi.squirrelsbox.inventory.presentation.item.HomeItemAdapter
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab

class DownloadedItemFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentDownloadedItemBinding

    private val viewModel : DownloadedItemViewModel by activityViewModels()
    private val viewModelFAB : AddDialogViewFab by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFAB.setData("item")
        //init data binding in a fragment
        binding = FragmentDownloadedItemBinding.inflate(layoutInflater)

        //this value must be returned
        val view : View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val itemItemAdapter = DownloadedItemAdapter()
        binding.allItemsRv.adapter = itemItemAdapter

        viewModel.fetchNewsFeed(requireContext())

        viewModel.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        // Inflate the layout for this fragment
        return view
    }
}