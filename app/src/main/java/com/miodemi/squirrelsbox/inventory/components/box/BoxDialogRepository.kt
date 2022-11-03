package com.miodemi.squirrelsbox.inventory.components.box

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.inventory.data.BoxData
import java.util.*

class BoxDialogRepository {

    //Reference to database
    private lateinit var database: DatabaseReference


    fun storeData(name: String, boxType: Boolean, privateLink: String, download: Boolean, favourite: Boolean) {
        database = FirebaseDatabase.getInstance().getReference("boxes")

        val id = UUID.randomUUID().toString();

        val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val currentDate = sdf.format(Date())

        val boxData = BoxData(id, name, currentDate, boxType, privateLink, download, favourite)
        database.child(name).setValue(boxData)
    }

    fun updateData(boxId: String, boxName: String) {

        database = FirebaseDatabase.getInstance().getReference("boxes").child(boxId)
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