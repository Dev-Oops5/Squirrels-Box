package com.miodemi.squirrelsbox.inventory.application.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.infrastructure.ItemDialogRepository

class ItemDialogViewModel : ViewModel() {

    private val sectionRepository = ItemDialogRepository()

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _boxId = MutableLiveData<String>()
    val boxId: LiveData<String> = _boxId

    private val _boxName = MutableLiveData<String>()
    val boxName: LiveData<String> = _boxName

    private val _sectionId = MutableLiveData<String>()
    val sectionId: LiveData<String> = _sectionId

    private val _sectionName = MutableLiveData<String>()
    val sectionName: LiveData<String> = _sectionName

    fun setId(currentId: String) {
        _id.value = currentId
    }

    fun setBoxId(currentBoxId: String) {
        _boxId.value = currentBoxId
    }

    fun setSectionId(currentSectionId: String) {
        _sectionId.value = currentSectionId
    }

    fun setName(currentName: String) {
        _name.value = currentName
    }

    fun setBoxName(currentName: String) {
        _boxName.value = currentName
    }

    fun setSectionName(currentName: String) {
        _sectionName.value = currentName
    }

    fun storeData(
        name: String, color: String, description: String, amount: Int, picture: String,
        favourite: Boolean){
        _sectionId.value?.let {
            _boxId.value?.let { it1 ->
                sectionRepository.storeData(it1, it, name, color, description, amount, picture, favourite) } }
    }

    fun updateFastData(name: String, color: String, amount: Int){
        _id.value?.let {
            _sectionId.value?.let { it2 ->
                _boxId.value?.let { it1 ->
                    sectionRepository.updateFastData(it1, it2, it, name, color, amount) } }
        }
    }

    fun deleteData(){
        _id.value?.let {
            _sectionId.value?.let { it2 ->
                _boxId.value?.let { it1 ->
                    sectionRepository.deleteData(it1, it2, it) } }
        }
    }

}