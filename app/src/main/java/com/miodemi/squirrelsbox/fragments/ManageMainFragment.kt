package com.miodemi.squirrelsbox.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.adapter.HomeBoxAdapter
import com.miodemi.squirrelsbox.data.BoxData
import com.miodemi.squirrelsbox.databinding.FragmentManageMainBinding
import kotlinx.android.synthetic.main.fragment_manage_main.*

class ManageMainFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentManageMainBinding

    //Reference to database
    private lateinit var database: DatabaseReference

    //Recycler view
    lateinit var activeServiceRecyclerView: RecyclerView

    //Data reader
    lateinit var activeServiceArrayList: ArrayList<BoxData>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentManageMainBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        //Recycler view adapter
        val allBoxesRvv : RecyclerView = view.findViewById(R.id.allBoxesRv)
        activeServiceRecyclerView = allBoxesRvv
        activeServiceRecyclerView.layoutManager = LinearLayoutManager(activity)
        activeServiceRecyclerView.setHasFixedSize(true)

        //Data reader
        activeServiceArrayList = arrayListOf<BoxData>()

        getAllBoxesData()

        // Inflate the layout for this fragment
        return view
    }

    private fun getAllBoxesData() {
        //create adapter
        val adapter = HomeBoxAdapter(activeServiceArrayList)
        var counter = 0
        var validator = ""
        //change branch
        database = FirebaseDatabase.getInstance().getReference("boxes")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (servicesSnapshots in snapshot.children) {
                        //Change value
                        val activeService = servicesSnapshots.getValue(BoxData::class.java)
                        if (activeService != null) {
                            activeServiceArrayList.add(activeService)
                        }
                    }
                }
                activeServiceRecyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}