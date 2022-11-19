package com.miodemi.squirrelsbox.inventory.infrastructure

import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.miodemi.squirrelsbox.inventory.domain.SectionData
import com.miodemi.squirrelsbox.session.domain.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
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
        database.child(id).setValue(sectionData)
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

    fun getSectionsByBoxId(boxId: String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading())

        val sections = FirebaseDatabase.getInstance().getReference("boxes").child(boxId)
            .child("sections").get().await()

        sections?.let {
            val data: MutableList<SectionData?> = ArrayList()
            for (ds in sections.children) {
                data.add(ds.getValue(SectionData::class.java))
            }
            emit(State.success(data))
        }
    }.catch {
        emit(State.failed(it.message!!))
    }.flowOn(Dispatchers.IO)

}