package com.miodemi.squirrelsbox.session.fragments

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentRegisterUserInfoBinding
import com.miodemi.squirrelsbox.profile.fragments.RegisterMobileFragment
import com.miodemi.squirrelsbox.session.navigation.OpenHelper
import com.miodemi.squirrelsbox.session.navigation.RegisterViewModel

class RegisterUserInfoFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentRegisterUserInfoBinding

        private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    private lateinit var username:String
    private lateinit var email:String
    private lateinit var birthday:String
    private lateinit var password:String
    private lateinit var rePassword:String
    private lateinit var auth: FirebaseAuth

    lateinit var dbHelper: OpenHelper

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
                viewModel.registerNewUser(username, email, birthday)
                viewModel.register(email, password, this.activity)

                dbHelper = OpenHelper(this.requireActivity())

                dbHelper.nuevoUser(
                    binding.usernameEt.text.toString(),
                    binding.passwordEt.text.toString()
                )

                val activity = v.context as AppCompatActivity
                //declare instance you want to replace
                activity.supportFragmentManager.beginTransaction()
                    .replace(R.id.registerFLy, userMobileFragment).addToBackStack(null)
                    .commit()
            }
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun validateForm(): Boolean {
        var valid = true

        username = binding.usernameEt.text.toString()
        if (username.isNullOrEmpty()) {
            binding.usernameEt.error = "Required."
            valid = false
            Toast.makeText(this.activity, "Username must not be empty", Toast.LENGTH_SHORT).show()
        } else {
            binding.usernameEt.error = null
        }

        email = binding.emailEt.text.toString()
        if (email.isNullOrEmpty()) {
            binding.emailEt.error = "Required."
            valid = false
            Toast.makeText(this.activity, "Email must not be empty", Toast.LENGTH_SHORT).show()
        } else {
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.emailEt.error = "Required."
                valid = false
                Toast.makeText(this.activity, "Email must be a valid email", Toast.LENGTH_SHORT).show()
            } else {
                binding.emailEt.error = null
            }
        }

        birthday = binding.birthdayEt.text.toString()

        password = binding.passwordEt.text.toString()
        if (password.isNullOrEmpty()) {
            binding.passwordEt.error = "Required."
            valid = false
            Toast.makeText(this.activity, "Password must not be empty", Toast.LENGTH_SHORT).show()
        } else {
            binding.passwordEt.error = null
        }

        rePassword = binding.rePasswordEt.text.toString()
        if (rePassword.isNullOrEmpty()) {
            binding.rePasswordEt.error = "Required."
            valid = false
            Toast.makeText(this.activity, "Confirm Password must not be empty", Toast.LENGTH_SHORT).show()
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