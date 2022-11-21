package com.miodemi.squirrelsbox.inventory.application.section

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.domain.SectionData
import com.miodemi.squirrelsbox.inventory.infrastructure.BoxOpenHelper

class DownloadedSectionViewModel  : ViewModel() {

    private val _idD = MutableLiveData<String>()
    val idD: LiveData<String> = _idD

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _sectionDataLiveData = MutableLiveData<List<SectionData>>()
    val sectionDataLiveData: LiveData<List<SectionData>> = _sectionDataLiveData

    fun setBoxId(currentId: String) {
        _idD.value = currentId
    }

    fun setName(currentName: String) {
        _name.value = currentName
    }

    fun fetchNewsFeed(context: Context) {
        val repository = BoxOpenHelper(context)
        _name.value?.let {repository.fetchNewsFeedSection(_sectionDataLiveData, it)}
    }
}