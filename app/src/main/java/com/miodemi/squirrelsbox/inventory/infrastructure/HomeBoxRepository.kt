package com.miodemi.squirrelsbox.inventory.infrastructure

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.inventory.domain.BoxData

class HomeBoxRepository {

    private val database = Firebase.database
    private val newsFeedReference = database.getReference("boxes")

    fun fetchNewsFeed(liveData: MutableLiveData<List<BoxData>>) {

        val auth = FirebaseAuth.getInstance().currentUser?.email.toString()

        newsFeedReference
            .orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    var newsFeedItems: List<BoxData> = emptyList()
                    newsFeedItems = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(BoxData::class.java)!!.copy(id = dataSnapshot.key!!)
                    }
                    newsFeedItems.filter { it.author == auth }
                    liveData.postValue(newsFeedItems)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
    }

}