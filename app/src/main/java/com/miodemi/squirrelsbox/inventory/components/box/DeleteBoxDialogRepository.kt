package com.miodemi.squirrelsbox.inventory.components.box

import android.util.Log
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class DeleteBoxDialogRepository {

    //Reference to database
    private lateinit var database: DatabaseReference

    fun deleteData(boxId : String) {

        database = FirebaseDatabase.getInstance().getReference("boxes")
        database.child(boxId).removeValue()
            .addOnSuccessListener {
                Log.i("RemoveBox", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("RemoveBox", "Not done yet :(")
            }
    }

}