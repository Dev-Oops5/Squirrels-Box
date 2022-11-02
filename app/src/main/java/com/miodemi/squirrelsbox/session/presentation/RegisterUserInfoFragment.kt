package com.miodemi.squirrelsbox.session.presentation

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.miodemi.squirrelsbox.R
import com.miodemi.squirrelsbox.databinding.FragmentRegisterUserInfoBinding
import com.miodemi.squirrelsbox.session.domain.State
import com.miodemi.squirrelsbox.profile.presentation.RegisterMobileFragment
import com.miodemi.squirrelsbox.session.application.RegisterViewModel

class RegisterUserInfoFragment : Fragment() {

    //binding
    internal lateinit var binding: FragmentRegisterUserInfoBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    //TextBox
    private lateinit var username:String
    private lateinit var email:String
    private lateinit var birthday:String
    private lateinit var password:String
    private lateinit var rePassword:String
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
        auth = FirebaseAuth.getInstance()
        binding.registerContinueBtn.setOnClickListener { v: View ->


            if (validateForm()) {

                // Watching for signUp result
                lifecycleScope.launchWhenStarted {
                    viewModel.signUp(username, email, password, birthday).collect {
                        when (it) {
                            //is State.Loading -> TODO()

                            is State.Success -> {
                                Toast.makeText(this@RegisterUserInfoFragment.activity, it.data.toString(), Toast.LENGTH_SHORT).show()

                                val activity = v.context as AppCompatActivity
                                //declare instance you want to replace
                                activity.supportFragmentManager.beginTransaction()
                                    .replace(R.id.registerFLy, userMobileFragment).addToBackStack(null)
                                    .commit()
                            }

                            is State.Failed -> {
                                Toast.makeText(this@RegisterUserInfoFragment.activity, it.message, Toast.LENGTH_SHORT).show()
                            }
                            else -> {"FixThis"}
                        }
                    }
                }

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