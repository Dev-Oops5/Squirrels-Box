package com.miodemi.squirrelsbox.profile.application

import androidx.annotation.Nullable
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

    fun setUsername(userName: String) {
        _username.value = userName
    }

    fun setBirthday(birthDay: String) {
        _birthday.value = birthDay
    }

/*
    fun setEmail(email: String) {
        _email.value = email
}*/

/*
    fun updateUsername(userName: String){
        _email.value?.let { profileRepository.updateUsername(it, userName) }
    }

    fun updateBirthday(birthday: String){
        _email.value?.let { profileRepository.updateBirthday(it, birthday) }
    }
*/

    fun updateinfo(birthday: String,username: String){
        profileRepository.updateinfo(birthday,username)
    }

    fun getUsername(){
//        profileRepository.getUsername()
    }

}