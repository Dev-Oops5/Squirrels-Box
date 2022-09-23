package com.miodemi.squirrelsbox.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.adapter.HomeBoxAdapter
import com.miodemi.squirrelsbox.data.models.BoxData
import com.miodemi.squirrelsbox.databinding.FragmentManageMainBinding

class ManageMainFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentManageMainBinding

    //Reference to database
    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentManageMainBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root



        // Inflate the layout for this fragment
        return view
    }

}