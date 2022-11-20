package com.miodemi.squirrelsbox.inventory.application.item

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import com.miodemi.squirrelsbox.inventory.infrastructure.BoxOpenHelper

class DownloadedItemViewModel : ViewModel() {

    private val _boxIdD = MutableLiveData<String>()
    val idD: LiveData<String> = _boxIdD

    private val _sectionIdD = MutableLiveData<String>()
    val sectionIdD: LiveData<String> = _sectionIdD

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _itemDataLiveData = MutableLiveData<List<ItemData>>()
    val itemDataLiveData: LiveData<List<ItemData>> = _itemDataLiveData

    fun setBoxId(currentBoxId: String) {
        _boxIdD.value = currentBoxId
    }

    fun setSectionId(currentSectionId: String) {
        _sectionIdD.value = currentSectionId
    }

    fun setSectionName(currentSectionName: String) {
        _name.value = currentSectionName
    }

    fun fetchNewsFeed(context: Context) {
        val repository = BoxOpenHelper(context)

        _boxIdD.value?.let {
            _sectionIdD.value?.let { it1 ->
                repository.fetchNewsFeedItem(_itemDataLiveData, it, it1) }
        }
    }

}