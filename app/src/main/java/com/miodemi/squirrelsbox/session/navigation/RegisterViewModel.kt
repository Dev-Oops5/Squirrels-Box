package com.miodemi.squirrelsbox.session.navigation

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.*

class RegisterViewModel: ViewModel() {
    private val repository = UserRepository()

    fun signUp(username:String, email: String, password: String, birthday:String) =
        repository.signUp(username, email, password, birthday)


    fun login(email:String, password:String) =
        repository.login(email, password)

    fun forgotPassword(email: String) =
        repository.forgotPassword(email)

    fun signOut() =
        repository.signOut()


}