package com.miodemi.squirrelsbox.data

import android.media.Image

data class ItemData(val id:String? = null,
                    val name:String? = null,
                    val dateCreated:String? = null,
                    val color:String? = null,
                    val description:String? = null,
                    val amount:Int? = null,
                    val picture:Image? = null,
                    val favourite:Boolean? = null)
