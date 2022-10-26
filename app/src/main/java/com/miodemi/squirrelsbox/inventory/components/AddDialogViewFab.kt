package com.miodemi.squirrelsbox.inventory.components

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