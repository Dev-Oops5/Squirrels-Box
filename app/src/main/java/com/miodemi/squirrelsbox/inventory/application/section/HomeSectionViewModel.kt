package com.miodemi.squirrelsbox.inventory.application.section

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.domain.SectionData
import com.miodemi.squirrelsbox.inventory.infrastructure.HomeSectionRepository

class HomeSectionViewModel : ViewModel() {

    private val repository = HomeSectionRepository()

    private val _id = MutableLiveData<String>()
    val id: LiveData<String> = _id

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _sectionDataLiveData = MutableLiveData<List<SectionData>>()
    val sectionDataLiveData: LiveData<List<SectionData>> = _sectionDataLiveData

    fun setBoxId(currentId: String) {
        _id.value = currentId
    }

    fun setName(currentName: String) {
        _name.value = currentName
    }

    fun fetchNewsFeed() {
        _id.value?.let {repository.fetchNewsFeed(_sectionDataLiveData, it)}
    }
    fun fetchNewsFeedSearch(sectionName: String) {
        _id.value?.let {repository.fetchNewsFeedSearch(_sectionDataLiveData, it, sectionName)}
    }

}