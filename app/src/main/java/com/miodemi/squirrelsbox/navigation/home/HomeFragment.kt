package com.miodemi.squirrelsbox.navigation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentHomeBinding
import com.miodemi.squirrelsbox.databinding.FragmentMenuProfileBinding
import com.miodemi.squirrelsbox.detail.homebox.HomeBoxViewModel
import com.miodemi.squirrelsbox.detail.homebox.HomeBoxAdapter

class HomeFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentHomeBinding

    private val viewModel: HomeBoxViewModel by lazy {
        ViewModelProvider(this)[HomeBoxViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
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