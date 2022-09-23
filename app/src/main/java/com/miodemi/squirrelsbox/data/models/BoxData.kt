package com.miodemi.squirrelsbox.data.models

import java.util.*

data class BoxData(
    val id:String? = null,
    val name:String? = null,
    val dateCreated:String? = null,
    val boxType:Boolean? = null,
    val privateLink: String? = null,
    val download:Boolean? = null,
    val favourite:Boolean? = null)

