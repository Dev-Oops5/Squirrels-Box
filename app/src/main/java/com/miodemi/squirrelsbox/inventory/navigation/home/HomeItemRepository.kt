package com.miodemi.squirrelsbox.inventory.navigation.home

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.inventory.data.ItemData
import com.miodemi.squirrelsbox.inventory.data.SectionData

class HomeItemRepository {

    private val database = Firebase.database
    private val newsFeedReference = database.getReference("boxes")

    fun fetchNewsFeed(liveData: MutableLiveData<List<ItemData>>, boxId: String, sectionId: String) {
        newsFeedReference
            .child(boxId).child("sections").child(sectionId).child("items")
            .orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val newsFeedItems: List<ItemData> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(ItemData::class.java)!!.copy(id = dataSnapshot.key!!)
                    }

                    liveData.postValue(newsFeedItems)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
    }

}