package com.miodemi.squirrelsbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.miodemi.squirrelsbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logInBTN.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}