package com.miodemi.squirrelsbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.miodemi.squirrelsbox.databinding.ActivityMainBinding
import com.miodemi.squirrelsbox.session.domain.State
import com.miodemi.squirrelsbox.session.application.RegisterViewModel

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    //TextBox
    private lateinit var username:String
    private lateinit var password:String

    //private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInBTN.setOnClickListener {

            if (validateForm()) {
                username = binding.usernameET.text.toString()
                password = binding.passwordET.text.toString()

                // Watching for signUp result
                lifecycleScope.launchWhenStarted {
                    viewModel.login(username, password).collect {
                        when (it) {
                            //is State.Loading -> TODO()

                            is State.Success -> {
                                Toast.makeText(this@MainActivity, it.data.toString(), Toast.LENGTH_SHORT).show()

                                val intent = Intent(this@MainActivity, HomeActivity::class.java)
                                // start your next activity
                                startActivity(intent)
                            }

                            is State.Failed -> {
                                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()
                            }
                            else -> {"FixThis"}
                        }
                    }
                }
            }

        }

        binding.linkNotAccTV.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }

    private fun validateForm(): Boolean {
        var valid = true

        username = binding.usernameET.text.toString()
        if (username.isNullOrEmpty()) {
            binding.usernameET.error = "Required."
            valid = false
            Toast.makeText(this, "Username must not be empty", Toast.LENGTH_SHORT).show()
        } else {
            binding.usernameET.error = null
        }

        password = binding.passwordET.text.toString()
        if (password.isNullOrEmpty()) {
            binding.passwordET.error = "Required."
            valid = false
            Toast.makeText(this, "Password must not be empty", Toast.LENGTH_SHORT).show()
        } else {
            binding.passwordET.error = null
        }

        return valid
    }
}