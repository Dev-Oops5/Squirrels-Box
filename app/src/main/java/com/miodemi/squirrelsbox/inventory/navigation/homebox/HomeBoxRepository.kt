package com.miodemi.squirrelsbox.inventory.navigation.homebox

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.inventory.data.BoxData

class HomeBoxRepository {

    private val database = Firebase.database
    private val newsFeedReference = database.getReference("boxes")

    fun fetchNewsFeed(liveData: MutableLiveData<List<BoxData>>) {
        newsFeedReference
            .orderByChild("id")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val newsFeedItems: List<BoxData> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(BoxData::class.java)!!.copy(id = dataSnapshot.key!!)
                    }

                    liveData.postValue(newsFeedItems)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
    }



//    fun updateFavoriteStatus(id: String, isFavorite: Boolean) {
//        newsFeedReference.child(id).child("favorite").setValue(isFavorite)
//    }

}