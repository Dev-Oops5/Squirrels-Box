package com.miodemi.squirrelsbox.inventory.application.box

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.inventory.infrastructure.HomeBoxRepository

class HomeBoxViewModel : ViewModel() {

    private val repository = HomeBoxRepository()

    private val _boxDataLiveData = MutableLiveData<List<BoxData>>()
    val boxDataLiveData: LiveData<List<BoxData>> = _boxDataLiveData

    fun fetchNewsFeed() {
        repository.fetchNewsFeed(_boxDataLiveData)
    }
//    fun updateFavoriteStatus(id: String, isFavorite: Boolean) {
//        repository._boxDataLiveData(id, isFavorite)
//    }


}