package com.miodemi.squirrelsbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.miodemi.squirrelsbox.databinding.ActivityMainBinding
import com.miodemi.squirrelsbox.databinding.ActivityRegisterBinding
import com.miodemi.squirrelsbox.fragments.RegisterMobileFragment
import com.miodemi.squirrelsbox.fragments.RegisterUserInfoFragment
import com.miodemi.squirrelsbox.ui.profile.MenuProfileFragment
import kotlinx.android.synthetic.main.fragment_register_user_info.*

class RegisterActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityRegisterBinding

    //UI Fragments
    private val userInfoFragment = RegisterUserInfoFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //show fragment
        replaceFragment(userInfoFragment)

    }

    //basic function for fragment replacement
    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.registerFLy, fragment)
        transaction.addToBackStack(null).commit()
    }
}