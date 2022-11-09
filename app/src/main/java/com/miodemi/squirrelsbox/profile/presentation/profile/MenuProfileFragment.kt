package com.miodemi.squirrelsbox.profile.presentation.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.databinding.FragmentMenuProfileBinding

class MenuProfileFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentMenuProfileBinding
    private var actBox = 5
    private var maxBox = 10

    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentMenuProfileBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        //assign current and max boxes amount in text view
        binding.actBoxTV.text = actBox.toString()
        binding.maxBoxTV.text = maxBox.toString()

        //assign  current and max boxes amount in progress bar
        binding.actBoxesPb.max = maxBox
        binding.actBoxesPb.progress = actBox

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if(user != null){
            binding.usernameEt.setText(user.email)
        }

        binding.profilePicIv.setOnClickListener {
            openGalery()
        }
    }

    private val startForActivityGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        result->
        if(result.resultCode == Activity.RESULT_OK){
            val data = result.data?.data
            binding.profilePicIv.setImageURI(data)
        }
    }


    private fun openGalery(){

        val intent = Intent()

        intent.action = Intent.ACTION_GET_CONTENT

        intent.type = "image/*"

        startForActivityGallery.launch(intent)
    }

}