package com.miodemi.squirrelsbox.profile.navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddDialogViewFab : ViewModel()  {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun setData(createdData: String) {
        _data.value = createdData
    }

}