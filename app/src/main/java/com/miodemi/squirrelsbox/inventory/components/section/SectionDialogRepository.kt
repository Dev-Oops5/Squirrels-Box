package com.miodemi.squirrelsbox.inventory.components.section

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.inventory.data.BoxData
import com.miodemi.squirrelsbox.inventory.data.SectionData
import java.util.*

class SectionDialogRepository {

    //Reference to database
    private lateinit var database: DatabaseReference

    fun storeData(boxId: String, name: String, color: String, favourite: Boolean) {

        database = FirebaseDatabase.getInstance().getReference("boxes").child(boxId).child("sections")

        val id = UUID.randomUUID().toString();

        val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val currentDate = sdf.format(Date())

        val sectionData = SectionData(id, name, currentDate, color, favourite,boxId)
        database.child(name).setValue(sectionData)
    }

    fun updateData(boxId: String, sectionId: String, sectionName: String, color : String) {

        database = FirebaseDatabase.getInstance().getReference("boxes")
            .child(boxId).child("sections")
        val section = mapOf<String,String>(
            "name" to sectionName,
            "color" to color
        )
        database.child(sectionId).updateChildren(section)
            .addOnSuccessListener {
                Log.i("UpdateSection", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("UpdateSection", "Not done yet :(")
            }
    }

    fun deleteData(boxId: String, sectionId : String) {

        database = FirebaseDatabase.getInstance().getReference("boxes").child(boxId).child("sections")
        database.child(sectionId).removeValue()
            .addOnSuccessListener {
                Log.i("RemoveSection", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("RemoveSection", "Not done yet :(")
            }
    }

}