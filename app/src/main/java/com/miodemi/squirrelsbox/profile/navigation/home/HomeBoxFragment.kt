package com.miodemi.squirrelsbox.profile.navigation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.miodemi.squirrelsbox.databinding.FragmentHomeBoxBinding
import com.miodemi.squirrelsbox.inventory.navigation.homebox.HomeBoxViewModel
import com.miodemi.squirrelsbox.inventory.navigation.homebox.HomeBoxAdapter

class HomeBoxFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeBoxBinding

    private val viewModel: HomeBoxViewModel by lazy {
        ViewModelProvider(this)[HomeBoxViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentHomeBoxBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val itemBoxAdapter = HomeBoxAdapter()
        binding.allBoxesRv.adapter = itemBoxAdapter

        viewModel.fetchNewsFeed()

        // Inflate the layout for this fragment
        return view
    }

}