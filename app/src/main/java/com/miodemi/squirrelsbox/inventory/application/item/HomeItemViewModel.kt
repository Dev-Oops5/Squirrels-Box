package com.miodemi.squirrelsbox.inventory.application.item

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import com.miodemi.squirrelsbox.inventory.infrastructure.HomeItemRepository

class HomeItemViewModel : ViewModel() {

    private val repository = HomeItemRepository()

    private val _boxId = MutableLiveData<String>()
    val id: LiveData<String> = _boxId

    private val _sectionId = MutableLiveData<String>()
    val sectionId: LiveData<String> = _sectionId

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _itemDataLiveData = MutableLiveData<List<ItemData>>()
    val itemDataLiveData: LiveData<List<ItemData>> = _itemDataLiveData

    fun setBoxId(currentBoxId: String) {
        _boxId.value = currentBoxId
    }

    fun setSectionId(currentSectionId: String) {
        _sectionId.value = currentSectionId
    }

    fun setSectionName(currentSectionName: String) {
        _name.value = currentSectionName
    }

    fun fetchNewsFeed() {
        _boxId.value?.let {
            _sectionId.value?.let { it1 ->
                repository.fetchNewsFeed(_itemDataLiveData, it, it1) }
        }
    }

}