package com.miodemi.squirrelsbox.profile.presentation.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.miodemi.squirrelsbox.databinding.FragmentMenuProfileBinding
import com.miodemi.squirrelsbox.profile.application.ProfileViewModel
import java.text.Bidi

class MenuProfileFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentMenuProfileBinding
    //private val profileData = mutableListOf<profileData>()

    private val viewmodel : ProfileViewModel by activityViewModels()
//    private val pR = profileRepository()

    private var actBox = 5
    private var maxBox = 10

    lateinit var auth: FirebaseAuth

    private lateinit var username:String
    private lateinit var email:String
    private lateinit var birthday:String

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

        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser

        if(user!=null) {
            viewmodel.setEmail(user.email.toString())
            binding.usernameEt.setText(user.email)
        }
            //binding.usernameEt.setText(user.email)

        //binding.usernameEt.setText(
        //binding.usernameEt.setText(viewmodel.username.value)
        //binding.usernameEt.text = get

        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.updateInfoBtn.setOnClickListener{
            username = binding.usernameEt.text.toString()
            birthday = binding.birthdayEt.text.toString()

            viewmodel.updateUsername(username)
            viewmodel.updateBirthday(birthday)

            binding.usernameEt.text.clear()
            binding.birthdayEt.text.clear()
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