package com.miodemi.squirrelsbox.session.navigation

import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase
import com.miodemi.squirrelsbox.session.data.UserData
import java.util.*


class UserRepository {

    private val database = FirebaseDatabase.getInstance().getReference("users")
    private var auth = FirebaseAuth.getInstance()
    private val firebaseUserMutableLiveData: MutableLiveData<FirebaseUser>? = null
    private val userLoggedMutableLiveData: MutableLiveData<Boolean>? = null

    fun getFirebaseUserMutableLiveData(): MutableLiveData<FirebaseUser>? {
        return firebaseUserMutableLiveData
    }

    fun getUserLoggedMutableLiveData(): MutableLiveData<Boolean>? {
        return userLoggedMutableLiveData
    }


    fun addNewUser(user:UserData) {
        //val id = database.push().key!!
        val id = UUID.randomUUID().toString();
        user.id = id
        database.child(id).setValue(user)
    }

    fun registerUser(email: String, password:String, context: FragmentActivity?) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "User registered successfully", Toast.LENGTH_SHORT).show()
                } else{
                    try {
                        throw it.exception ?: java.lang.Exception("Invalid authentication")
                    } catch (e: FirebaseAuthWeakPasswordException) {
                        Toast.makeText(context, "Authentication failed, Password should be at least 6 characters", Toast.LENGTH_SHORT).show()
                    } catch (e: FirebaseAuthInvalidCredentialsException) {
                        Toast.makeText(context, "Authentication failed, Invalid email entered", Toast.LENGTH_SHORT).show()
                    } catch (e: FirebaseAuthUserCollisionException) {
                        Toast.makeText(context, "Authentication failed, Email already registered", Toast.LENGTH_SHORT).show()
                    } catch (e: Exception) {
                        Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }

    fun login(email: String, password: String, context: FragmentActivity?) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    firebaseUserMutableLiveData?.postValue(auth.currentUser)
                    Toast.makeText(context, "Login successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, it.exception?.message, Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData?.postValue(true)
    }

}