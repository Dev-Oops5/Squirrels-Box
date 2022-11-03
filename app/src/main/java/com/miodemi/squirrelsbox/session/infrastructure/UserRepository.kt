package com.miodemi.squirrelsbox.session.infrastructure

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import com.miodemi.squirrelsbox.session.domain.State
import com.miodemi.squirrelsbox.session.domain.UserData
import java.util.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await


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

    fun signUp(username:String, email: String, password: String, birthday:String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading())
        val auth = Firebase.auth
        val data = auth.createUserWithEmailAndPassword(email, password).await()

        data.user?.let {
            val userData = UserData(null, username, email, birthday)
            addNewUser(userData)

            emit(State.success("User registered successfully"))
        }
    }.catch { e ->
        val error: String =
        when (e) {
            is FirebaseAuthWeakPasswordException -> "Authentication failed, Password should be at least 6 characters"
            is FirebaseAuthInvalidCredentialsException -> "Authentication failed, Invalid email entered"
            is FirebaseAuthUserCollisionException -> "Authentication failed, Email already registered"

            else -> e.message.toString()
        }

        emit(State.failed(error))
        //emit(State.failed(it.message!!))
    }.flowOn(Dispatchers.IO)

    private fun addNewUser(user:UserData) {
        //val id = database.push().key!!
        val id = UUID.randomUUID().toString();
        user.id = id
        database.child(id).setValue(user)
    }


    fun login(email: String, password: String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading())
        auth.signInWithEmailAndPassword(email, password).await()
        emit(State.success("Login successfully"))
    }.catch {
        emit(State.failed(it.message!!))
    }.flowOn(Dispatchers.IO)


    fun forgotPassword(email: String): Flow<State<Any>> = flow<State<Any>> {
        emit(State.loading())
        auth.sendPasswordResetEmail(email).await()
        emit(State.success("Password reset email sent."))
    }.catch {
        emit(State.failed(it.message!!))
    }.flowOn(Dispatchers.IO)


    fun signOut() {
        auth.signOut()
        userLoggedMutableLiveData?.postValue(true)
    }

}