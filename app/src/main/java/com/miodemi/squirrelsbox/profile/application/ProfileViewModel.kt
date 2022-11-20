package com.miodemi.squirrelsbox.profile.application

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.profile.infraestructure.ProfileRepository

class ProfileViewModel: ViewModel() {

    private val profileRepository = ProfileRepository()

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _birthday = MutableLiveData<String>()
    val birthday: LiveData<String> = _birthday

    fun setEmail(email: String) {
        _email.value = email
    }
    fun setId(id: String) {
        _id.value = id
    }

/*
    fun setId(id: String) {
        _id.value = id
    }

    fun setBirthday(birthday: String) {
        _birthday.value = birthday
    }

    fun setUsername(username: String) {
        _username.value = username
    }
*/
    fun updateUsername(userName: String){
        _id.value?.let { profileRepository.updateUsername(it, userName) }
    }

    fun updateBirthday(birthday: String){
        _id.value?.let { profileRepository.updateBirthday(it, birthday) }
    }
}