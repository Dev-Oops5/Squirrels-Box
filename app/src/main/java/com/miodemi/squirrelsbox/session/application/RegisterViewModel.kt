package com.miodemi.squirrelsbox.session.application

import androidx.lifecycle.*
import com.miodemi.squirrelsbox.profile.infraestructure.ProfileRepository
import com.miodemi.squirrelsbox.session.infrastructure.UserRepository

class RegisterViewModel: ViewModel() {
    private val repository = UserRepository()
//    private val repository2 = ProfileRepository()

    fun signUp(username:String, email: String, password: String, birthday:String) =
        repository.signUp(username, email, password, birthday)

    fun login(email:String, password:String) =
        repository.login(email, password)

    fun forgotPassword(email: String) =
        repository.forgotPassword(email)

    fun signOut() =
        repository.signOut()


}