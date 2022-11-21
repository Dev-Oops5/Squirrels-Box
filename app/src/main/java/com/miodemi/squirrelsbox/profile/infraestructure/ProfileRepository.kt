package com.miodemi.squirrelsbox.profile.infraestructure

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

import com.miodemi.squirrelsbox.profile.application.ProfileViewModel


class ProfileRepository {

    private lateinit var database: DatabaseReference

    //private val profileViewModel = ProfileViewModel()

    fun updateinfo(birthday: String, username: String){
        database = FirebaseDatabase.getInstance().getReference("users")

        val auth = FirebaseAuth.getInstance()
        val userEmail = auth.currentUser?.email.toString()

        val newEmail = userEmail.replace("."," ")

        val user = mapOf<String,String>(
            "birthday" to birthday,
            "username" to username
        )

        database.child(newEmail).updateChildren(user)

        //database.child(newEmail).child("birthday").setValue(birthday)
        //database.child(newEmail).child("username").setValue(username)
    }


/*
    fun getUsername(){
        database = FirebaseDatabase.getInstance().getReference("users")

        val auth = FirebaseAuth.getInstance()
        val userEmail = auth.currentUser?.email.toString()

        val newEmail = userEmail.replace("."," ")

        database.child(newEmail).get().addOnSuccessListener {
            if(it.exists()){
                //profileViewModel = it.child("username").value
                val userN = it.child("username").value.toString()

                profileViewModel.setUsername(userN)
            }
        }
    }

 */

/*
    fun updateUsername(userName: String) {
        database = FirebaseDatabase.getInstance().getReference("users")

        val auth = FirebaseAuth.getInstance()
        val userEmail = auth.currentUser?.email.toString()
        val data = mapOf<String,String>(
            "username" to userName
        )
        database.child(userName).setValue(data)

        /*val username = mapOf<String,String>(
            "username" to userName
        )
        database.child(userEmail).updateChildren(username)
            .addOnSuccessListener {
                Log.i("UpdateUsername", "Successfully done :)")
            }
            .addOnFailureListener {
                Log.i("UpdateUsername", "Not done yet :(")
            }*/
    }
*/


    /*
    fun updateUsername(email: String, username: String){
        database = FirebaseDatabase.getInstance().getReference("users")

        //val auth = FirebaseAuth.getInstance()
        //val userEmail = auth.currentUser?.email.toString()

        val newEmail = email.replace("."," ")

        val user = mapOf<String,String>(
            "username" to username
        )
        //database.updateChildren(updateUsername(username))
        database.child(newEmail).updateChildren(user)

            .addOnSuccessListener {
                Log.i("UpdateBirthday", "Successfully done")
            }
            .addOnFailureListener {
                Log.i("UpdateBirthday", "Not done yet")
            }
    }

    fun updateBirthday(email: String, birthday: String) {
        database = FirebaseDatabase.getInstance().getReference("users")

        val newEmail = email.replace("."," ")

        val user = mapOf<String,String>(
            "birthday" to birthday
        )
        database.child(newEmail).updateChildren(user)
            .addOnSuccessListener {
                Log.i("UpdateBirthday", "Successfully done")
            }
            .addOnFailureListener {
                Log.i("UpdateBirthday", "Not done yet")
            }
    }
    */

/*
    fun setUser(email:String, username: String, birthday: String){
       emit(State.loading())

       val newEmail = email.replace("."," ")
      val user = FirebaseDatabase.getInstance().getReference("users").child(newEmail).get().await()

       user?.let{
            //val data = user.getValue<>()
            //emit(State.success(data!!))
       }
      database.child(newEmail).get().addOnSuccessListener {
              val user = database.child("username") }



        database = FirebaseDatabase.getInstance().getReference("users")

        val newEmail = email.replace("."," ")

        //database = FirebaseDatabase.getInstance().getReference("users")
        val key = database.child("$newEmail").push().key

        val user = profileData(username,birthday)
        val userValues = user.toMap()

        val userUpdate = hashMapOf<String, Any>(
            "/$newEmail/$key" to userValues,
        )
        database.updateChildren(userUpdate)
    }

 */
}