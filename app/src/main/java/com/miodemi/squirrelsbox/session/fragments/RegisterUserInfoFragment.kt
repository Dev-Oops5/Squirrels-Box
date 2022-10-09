package com.miodemi.squirrelsbox.session.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        binding.registerContinueBtn.setOnClickListener { v: View ->
            val activity = v.context as AppCompatActivity
            //declare instance you want to replace
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.registerFLy, userMobileFragment).addToBackStack(null)
                .commit()

            //Toast.makeText(this, "Confirm Password must be same that Password", Toast.LENGTH_SHORT).show()

            username = binding.usernameEt.text.toString()
            email = binding.emailEt.text.toString()
            birthday = binding.birthdayEt.text.toString()
            password = binding.passwordEt.text.toString()
            Toast.makeText(this.activity, "Pass to view model", Toast.LENGTH_SHORT).show()
            viewModel.registerNewUser(username, email, birthday, password)


//            var fr = fragmentManager?.beginTransaction()
//            fr?.replace(R.id.registerFLy, userMobileFragment)
//            fr?.commit()
        }

        // Inflate the layout for this fragment
        return view
    }

}