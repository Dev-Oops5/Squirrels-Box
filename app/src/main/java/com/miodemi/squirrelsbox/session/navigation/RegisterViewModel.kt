package com.miodemi.squirrelsbox.session.navigation

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.identity.SignInPassword
import com.miodemi.squirrelsbox.session.data.UserData

class RegisterViewModel: ViewModel() {

    private val repository = UserRepository()

    fun registerNewUser(username:String, email:String, birthday:String, password:String) {
        val user = UserData(null, username, email, birthday, password)
        repository.addNewUser(user)
    }

}