package com.miodemi.squirrelsbox.inventory.application.box

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.inventory.infrastructure.BoxOpenHelper

class DownloadedBoxViewModel() : ViewModel() {

    private val _boxDataDwLiveData = MutableLiveData<List<BoxData>>()
    val boxDataDwLiveData: LiveData<List<BoxData>> = _boxDataDwLiveData

    fun fetchNewsFeed(context: Context) {
        val repository = BoxOpenHelper(context)
        repository.fetchNewsFeedBox(_boxDataDwLiveData)
    }
}