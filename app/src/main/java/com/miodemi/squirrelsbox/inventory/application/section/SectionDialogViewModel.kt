package com.miodemi.squirrelsbox.inventory.application.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.infrastructure.SectionDialogRepository

class SectionDialogViewModel : ViewModel() {

    private val sectionRepository = SectionDialogRepository()

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _boxId = MutableLiveData<String>()
    val boxId: LiveData<String> = _boxId

    private val _boxName = MutableLiveData<String>()
    val boxName: LiveData<String> = _boxName

    fun setId(currentId: String) {
        _id.value = currentId
    }

    fun setBoxId(currentBoxId: String) {
        _boxId.value = currentBoxId
    }

    fun setName(currentName: String) {
        _name.value = currentName
    }

    fun setBoxName(currentName: String) {
        _boxName.value = currentName
    }

    fun storeData(name: String, color: String, favourite: Boolean){
        _boxId.value?.let { sectionRepository.storeData(it,name, color, favourite) }
    }

    fun updateData(currentName: String, currentColor: String){
        _id.value?.let { _boxId.value?.let { it1 -> sectionRepository.updateData(it1, it, currentName, currentColor) } }
    }

    fun deleteData(){
        _id.value?.let { _boxId.value?.let { it1 -> sectionRepository.deleteData(it1, it) } }
    }
}