package com.miodemi.squirrelsbox.profile.infraestructure

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProfileRepository {

    private lateinit var database: DatabaseReference

/*
    fun updateUsername(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("users")

        val auth = FirebaseAuth.getInstance()
        val userEmail = auth.currentUser?.email.toString()
        val data = mapOf<String,String>(
            "username" to userName
        )
        database.child(userName).setValue(data)

        /*val username = mapOf<String,String>(
            "username" to userName
        )
        database.child(userEmail).updateChildren(username)
            .addOnSuccessListener {
                Log.i("UpdateUsername", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("UpdateUsername", "Not done yet :(")
            }*/
    }
*/

    fun updateUsername(id: String, username: String){
        database = FirebaseDatabase.getInstance().getReference("users")

        //val auth = FirebaseAuth.getInstance()
        //val userEmail = auth.currentUser?.email.toString()

        val user = mapOf<String,String>(
            "username" to username
        )
        //database.updateChildren(updateUsername(username))
        database.child(id).updateChildren(user)
            .addOnSuccessListener {
                Log.i("UpdateBirthday", "Successfully done")
            }
            .addOnFailureListener {
                Log.i("UpdateBirthday", "Not done yet")
            }
    }
    fun updateBirthday(id: String, birthday: String) {
        database = FirebaseDatabase.getInstance().getReference("users")
        val user = mapOf<String,String>(
            "birthday" to birthday
        )
        database.child(id).updateChildren(user)
            .addOnSuccessListener {
                Log.i("UpdateBirthday", "Successfully done")
            }
            .addOnFailureListener {
                Log.i("UpdateBirthday", "Not done yet")
            }
    }
}