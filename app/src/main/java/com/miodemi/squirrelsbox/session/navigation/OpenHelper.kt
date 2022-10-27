package com.miodemi.squirrelsbox.session.navigation

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OpenHelper(context:Context):SQLiteOpenHelper(
    context, "usuarios.db", null, 1
) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val query = "create table users(_ID integer primary key autoincrement," +
                "username text, contrasenia text)"
        p0!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val query2="drop table users"
        p0!!.execSQL(query2)
        onCreate(p0)
    }

    fun nuevoUser(username:String, contrasenia:String){
        val datos=ContentValues()
        datos.put("username", username)
        datos.put("contrasenia", contrasenia)
        val db=this.writableDatabase.insert("users", null, datos)
    }
}