package com.miodemi.squirrelsbox.session.navigation

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.session.data.UserData

class RegisterViewModel: ViewModel() {
    private val repository = UserRepository()

    fun registerNewUser(username:String, email:String, birthday:String) {
        val user = UserData(null, username, email, birthday)
        repository.addNewUser(user)
    }

    fun register(email:String, password:String, context: FragmentActivity?) {
        repository.registerUser(email, password, context)
    }

    fun login(email:String, password:String, context: FragmentActivity?) {
        repository.login(email, password, context)
    }

    fun signOut() {
        repository.signOut()
    }

}