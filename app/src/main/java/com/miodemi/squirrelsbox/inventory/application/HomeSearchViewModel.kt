package com.miodemi.squirrelsbox.inventory.application

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeSearchViewModel : ViewModel() {

    private val _data = MutableLiveData<Bitmap>()
    val data: LiveData<Bitmap> = _data

    fun setData(createdData: Bitmap) {
        _data.value = createdData
    }


}