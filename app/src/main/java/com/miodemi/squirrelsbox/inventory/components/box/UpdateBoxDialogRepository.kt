package com.miodemi.squirrelsbox.inventory.components.box

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class UpdateBoxDialogRepository {

    //Reference to database
    private lateinit var database: DatabaseReference

    fun updateData(boxId : String, boxName : String) {

        database = FirebaseDatabase.getInstance().getReference("boxes")
        val box = mapOf<String,String>(
            "name" to boxName
        )
        database.child(boxId).updateChildren(box)
            .addOnSuccessListener {
                Log.i("UpdateBox", "Successfully done :)")
        }
            .addOnFailureListener {
                Log.i("UpdateBox", "Not done yet :(")
            }
    }

}