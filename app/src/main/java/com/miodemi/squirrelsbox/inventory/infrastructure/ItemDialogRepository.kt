package com.miodemi.squirrelsbox.inventory.infrastructure

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Picture
import android.icu.text.SimpleDateFormat
import android.media.Image
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import java.io.ByteArrayOutputStream
import java.io.File
import java.util.*
import kotlin.coroutines.coroutineContext

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
        database.child(name).setValue(itemData)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun storePicture(picture : Uri, fileName : String){

        val storageReference = FirebaseStorage.getInstance().getReference("/images/$fileName.jpg")

        storageReference.putFile(picture)
            .addOnSuccessListener {  }
            .addOnFailureListener {  }
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

    fun getImage(itemImage: String): Bitmap? {
        val storageRef = FirebaseStorage.getInstance().reference.child("images/$itemImage.jpg")

        val localfile = File.createTempFile("tempImage","jpg")
        storageRef.getFile(localfile)

        val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
        Log.i("Yes", "Image $itemImage retrieved")
        return bitmap
    }
}