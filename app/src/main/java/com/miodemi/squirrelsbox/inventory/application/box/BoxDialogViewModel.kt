package com.miodemi.squirrelsbox.inventory.application.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.infrastructure.BoxDialogRepository

class BoxDialogViewModel : ViewModel() {

//    internal val data = MutableLiveData<String>()
//
//    fun setData(newData: String) {
//        data.value = newData
//    }
    private val boxRepository = BoxDialogRepository()

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _date = MutableLiveData<String>()
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


    fun storeData(name: String, boxType: Boolean, privateLink: String, download: Boolean, favourite: Boolean){
        boxRepository.storeData(name, boxType, privateLink, download, favourite)
    }

    fun updateData(currentName: String){
        _id.value?.let { boxRepository.updateData(it,currentName) }
    }

    fun deleteData(){
        _id.value?.let { boxRepository.deleteData(it) }
    }
}