package com.miodemi.squirrelsbox.inventory.navigation.home

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miodemi.squirrelsbox.profile.navigation.AddDialogViewFab

class HomeSearchViewModel : ViewModel() {

    private val _data = MutableLiveData<Bitmap>()
    val data: LiveData<Bitmap> = _data

    fun setData(createdData: Bitmap) {
        _data.value = createdData
    }


}