package com.miodemi.squirrelsbox.inventory.domain

data class ItemData(val id:String? = null,
                    val name:String? = null,
                    val dateCreated:String? = null,
                    val color:String? = null,
                    val description:String? = null,
                    val amount:Int? = null,
                    val picture:String? = null,
                    val favourite:Boolean? = null,
                    val boxId: String? = null,
                    val sectionId: String? = null)
