package com.miodemi.squirrelsbox.inventory.infrastructure

import android.icu.text.SimpleDateFormat
import android.media.Image
import android.os.Build
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import java.util.*

class ItemDialogRepository {

    //Reference to database
    private lateinit var database: DatabaseReference

    fun storeData(boxId: String, sectionId: String , name: String, color: String,
                  description: String, amount: Int, picture: String,
                  favourite: Boolean) {

        database = FirebaseDatabase.getInstance().getReference("boxes")
            .child(boxId).child("sections")
            .child(sectionId).child("items")

        val id = UUID.randomUUID().toString();

        val sdf = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        } else {
            TODO("VERSION.SDK_INT < N")
        }
        val currentDate = sdf.format(Date())

        val itemData = ItemData(id, name, currentDate, color, description, amount, picture, favourite, boxId, sectionId)
        database.child(id).setValue(itemData)
    }

    fun updateFastData(boxId: String, sectionId: String , itemId: String, name: String, color: String,
                       amount: Int) {

        database = FirebaseDatabase.getInstance().getReference("boxes")
            .child(boxId).child("sections")
            .child(sectionId).child("items")
        val item = mapOf(
            "name" to name,
            "color" to color,
            "amount" to amount,
        )
        database.child(itemId).updateChildren(item)
            .addOnSuccessListener {
                Log.i("UpdateSection", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("UpdateSection", "Not done yet :(")
            }
    }

    fun updateFullData(boxId: String, sectionId: String , itemId: String, name: String, color: String,
                   description: String, amount: Int, picture: Image,
                   favourite: Boolean) {

        database = FirebaseDatabase.getInstance().getReference("boxes")
            .child(boxId).child("sections")
            .child(sectionId).child("items")
        val item = mapOf<String,String>(
            "name" to name,
            "color" to color,
            "description" to description,
            "amount" to amount.toString(),
            "picture" to picture.toString()
        )
        database.child(itemId).updateChildren(item)
            .addOnSuccessListener {
                Log.i("UpdateSection", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("UpdateSection", "Not done yet :(")
            }
    }

    fun deleteData(boxId: String, sectionId : String, itemId: String) {

        database = FirebaseDatabase.getInstance().getReference("boxes")
            .child(boxId).child("sections")
            .child(sectionId).child("items")
        database.child(itemId).removeValue()
            .addOnSuccessListener {
                Log.i("RemoveSection", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("RemoveSection", "Not done yet :(")
            }
    }

}