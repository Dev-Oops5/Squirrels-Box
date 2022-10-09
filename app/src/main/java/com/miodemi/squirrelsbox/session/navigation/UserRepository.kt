package com.miodemi.squirrelsbox.session.navigation

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.session.data.UserData
import java.util.*

class UserRepository {

    private val database = FirebaseDatabase.getInstance().getReference("users")

    fun addNewUser(user:UserData) {
        //val id = database.push().key!!
        val id = UUID.randomUUID().toString();
        user.id = id

        database.child(id).setValue(user)
    }

}