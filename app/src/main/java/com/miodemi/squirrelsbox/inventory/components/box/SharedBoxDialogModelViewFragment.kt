package com.miodemi.squirrelsbox.inventory.components.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedBoxDialogModelViewFragment : ViewModel() {

    private val repository = UpdateBoxDialogRepository()

    private val _id = MutableLiveData<String>("")
    val id: LiveData<String> = _id

    private val _name = MutableLiveData<String>("")
    val name: LiveData<String> = _name

    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    fun setId(currentId: String) {
        _id.value = currentId
    }

    fun setName(currentName: String) {
        _name.value = currentName
    }

    fun setDate(createdDate: String) {
        _date.value = createdDate
    }

}