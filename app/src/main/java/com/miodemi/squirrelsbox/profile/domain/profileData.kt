package com.miodemi.squirrelsbox.profile.domain

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class profileData(
    val birthday:String? = null,
    val email:String? = null,
    val id:String? = null,
    val username:String? = null,
){
    @Exclude
    fun toMap():Map<String,Any?>{
        return mapOf(
            "birthday" to birthday,
            "email" to email,
            "id" to id,
            "username" to username
        )
    }
}
