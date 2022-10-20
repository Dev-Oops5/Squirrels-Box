package com.miodemi.squirrelsbox.session.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentRegisterUserInfoBinding
import com.miodemi.squirrelsbox.profile.fragments.RegisterMobileFragment
import com.miodemi.squirrelsbox.session.navigation.RegisterViewModel

class RegisterUserInfoFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentRegisterUserInfoBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    internal lateinit var username:String
    internal lateinit var email:String
    internal lateinit var birthday:String
    internal lateinit var password:String
    internal lateinit var rePassword:String
    private lateinit var auth: FirebaseAuth

    //connected fragments
    private val userMobileFragment = RegisterMobileFragment()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init data binding in a fragment
        binding = FragmentRegisterUserInfoBinding.inflate(layoutInflater)
        //this value must be returned
        val view : View = binding.root

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.registerContinueBtn.setOnClickListener { v: View ->

            if (validateForm()) {
                viewModel.registerNewUser(username, email, birthday, password)
                createUserFirebase(email, password)

                val activity = v.context as AppCompatActivity
                //declare instance you want to replace
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.registerFLy, userMobileFragment).addToBackStack(null)
                    .commit()

            } else {
                Toast.makeText(this.activity, "Complete empty spaces", Toast.LENGTH_SHORT).show()
            }

        }

        // Inflate the layout for this fragment
        return view
    }

    //Create user in Firebase
    private fun createUserFirebase(email:String, password:String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    Toast.makeText(this.activity, "Authentication successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Authentication failed.",Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        username = binding.usernameEt.text.toString()
        if (TextUtils.isEmpty(username)) {
            binding.usernameEt.error = "Required."
            valid = false
        } else {
            binding.usernameEt.error = null
        }

        email = binding.emailEt.text.toString()
        if (TextUtils.isEmpty(email)) {
            binding.emailEt.error = "Required."
            valid = false
        } else {
            binding.emailEt.error = null
        }

        birthday = binding.birthdayEt.text.toString()
        if (TextUtils.isEmpty(birthday)) {
            binding.birthdayEt.error = "Required."
            valid = false
        } else {
            binding.birthdayEt.error = null
        }

        password = binding.passwordEt.text.toString()
        if (TextUtils.isEmpty(password)) {
            binding.passwordEt.error = "Required."
            valid = false
        } else {
            binding.passwordEt.error = null
        }

        rePassword = binding.rePasswordEt.text.toString()
        if (TextUtils.isEmpty(rePassword)) {
            binding.rePasswordEt.error = "Required."
            valid = false
        } else {
            binding.rePasswordEt.error = null
        }

        //Confirm password
        if (rePassword != password) {
            valid = false
            Toast.makeText(this.activity, "Confirm Password must be same that Password", Toast.LENGTH_SHORT).show()
        }

        return valid
    }

}