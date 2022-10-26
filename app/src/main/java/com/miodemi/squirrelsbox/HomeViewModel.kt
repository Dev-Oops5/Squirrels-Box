package com.miodemi.squirrelsbox

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _id = MutableLiveData<Int>()
    val id: LiveData<Int> = _id

    fun setId(currentId: Int) {
        _id.value = currentId
    }


}