package com.miodemi.squirrelsbox.inventory.infrastructure

import android.icu.text.SimpleDateFormat
import android.media.Image
import android.os.Build
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import com.miodemi.squirrelsbox.session.domain.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
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

    fun getItemsByBoxIdAndSectionId(boxId: String, sectionId: String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading())

        val items = FirebaseDatabase.getInstance().getReference("boxes").child(boxId)
            .child("sections").child(sectionId).child("items").get().await()

        items?.let {
            val data: MutableList<ItemData?> = ArrayList()
            for (ds in items.children) {
                data.add(ds.getValue(ItemData::class.java))
            }
            emit(State.success(data))
        }
    }.catch {
        emit(State.failed(it.message!!))
    }.flowOn(Dispatchers.IO)

}