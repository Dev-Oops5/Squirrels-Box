package com.miodemi.squirrelsbox.inventory.infrastructure

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.miodemi.squirrelsbox.inventory.domain.BoxData

class BoxOpenHelper(context: Context): SQLiteOpenHelper(
    context, "boxes.db", null, 1
) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table boxes(_ID integer primary key autoincrement," +
                "name text, dateCreated text, boxType boolean, privateLink text, download boolean, favourite boolean)"
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val query2 = "drop table boxes"
        p0!!.execSQL(query2)
        onCreate(p0)
    }

    fun newBox(b:BoxData){
        val data = ContentValues()
        data.put("name", b.name)
        data.put("dateCreated", b.dateCreated)
        data.put("boxType", b.boxType)
        data.put("privateLink", b.privateLink)
        data.put("download", b.download)
        data.put("favourite", b.favourite)
        val db = this.writableDatabase.insert("boxes", null, data)
    }
}