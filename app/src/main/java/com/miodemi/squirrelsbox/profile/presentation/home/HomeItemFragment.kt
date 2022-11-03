package com.miodemi.squirrelsbox.profile.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.miodemi.squirrelsbox.databinding.FragmentHomeItemBinding
import com.miodemi.squirrelsbox.profile.presentation.AddDialogViewFab
import com.miodemi.squirrelsbox.inventory.presentation.item.HomeItemAdapter
import com.miodemi.squirrelsbox.inventory.application.item.HomeItemViewModel

class HomeItemFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeItemBinding

    private val viewModel : HomeItemViewModel by activityViewModels()
    private val viewModelFAB : AddDialogViewFab by activityViewModels()

    lateinit var _addview : AddDialogViewFab


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFAB.setData("item")
        //init data binding in a fragment
        binding = FragmentHomeItemBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val itemItemAdapter = HomeItemAdapter()
        binding.allItemsRv.adapter = itemItemAdapter

        viewModel.fetchNewsFeed()

        viewModel.name.observe(viewLifecycleOwner) {
            binding.titleTv.text = it
        }

        // Inflate the layout for this fragment
        return view
    }

}