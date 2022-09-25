package com.miodemi.squirrelsbox.inventory.components.box

import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UpdateBoxDialogModelViewFragment : ViewModel() {

    private lateinit var database: DatabaseReference

    fun getData(){
        database.child("boxes").child("-N3-TE1AlOnNkX7CAbBp").get().addOnSuccessListener {
            Log.i("firebase", "Got value ${it.value}")
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }
}