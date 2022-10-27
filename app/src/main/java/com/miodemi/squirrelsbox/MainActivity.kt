package com.miodemi.squirrelsbox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.miodemi.squirrelsbox.databinding.ActivityMainBinding
import com.miodemi.squirrelsbox.session.navigation.OpenHelper

class MainActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var helper = OpenHelper(applicationContext)
        var db = helper.readableDatabase


        binding.logInBTN.setOnClickListener {
            var args = listOf<String>(binding.usernameET.text.toString(), binding.passwordET.text.toString()).toTypedArray()
            var rs = db.rawQuery("SELECT * FROM users WHERE username = ? AND contrasenia = ?", args)


            val intent = Intent(this, HomeActivity::class.java)

            if(rs.moveToNext())
                startActivity(intent)
            else
                Toast.makeText(applicationContext, "USUARIO O CONTRASEÑA INCORRECTO", Toast.LENGTH_LONG).show()
        }

        binding.linkNotAccTV.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}