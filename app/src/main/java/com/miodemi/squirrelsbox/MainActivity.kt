package com.miodemi.squirrelsbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.miodemi.squirrelsbox.databinding.ActivityMainBinding
import com.miodemi.squirrelsbox.session.navigation.RegisterViewModel

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    //TextBox
    private lateinit var username:String
    private lateinit var password:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInBTN.setOnClickListener {

            username = binding.usernameET.text.toString()
            password = binding.passwordET.text.toString()

            viewModel.login(username, password, this)

            val intent = Intent(this, HomeActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

        binding.linkNotAccTV.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}