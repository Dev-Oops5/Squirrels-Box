package com.miodemi.squirrelsbox.inventory.infrastructure

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.session.domain.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
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
        database.child(id).setValue(boxData)
    }

    fun updateData(boxId: String, boxName: String) {

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

    fun getBoxById(boxId: String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading())

        val box = FirebaseDatabase.getInstance().getReference("boxes").child(boxId).get().await()

        box?.let {
            val data = box.getValue<BoxData>()
            emit(State.success(data!!))
        }
    }.catch {
        emit(State.failed(it.message!!))
    }.flowOn(Dispatchers.IO)

}