package com.miodemi.squirrelsbox.session.navigation

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.session.data.UserData
import java.util.*


class UserRepository {

    private val database = FirebaseDatabase.getInstance().getReference("users")
    private lateinit var auth: FirebaseAuth

    fun addNewUser(user:UserData) {
        //val id = database.push().key!!
        val id = UUID.randomUUID().toString();
        user.id = id
        database.child(id).setValue(user)
    }

    fun registerUser(email: String, password:String, context: FragmentActivity?) {
        auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show()
                } else{
                    try {
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(context, "Authentication failed, Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context, "Authentication failed, Invalid email entered", Toast.LENGTH_SHORT).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(context, "Authentication failed, Email already registered", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

}