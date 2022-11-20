package com.miodemi.squirrelsbox.inventory.infrastructure

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.MutableLiveData
import com.miodemi.squirrelsbox.inventory.domain.BoxData
import com.miodemi.squirrelsbox.inventory.domain.ItemData
import com.miodemi.squirrelsbox.inventory.domain.SectionData

class BoxOpenHelper(context: Context): SQLiteOpenHelper(
    context, "boxes.db", null, 1
) {
    override fun onCreate(p0: SQLiteDatabase?) {
        val boxQuery = "CREATE TABLE boxes(Id TEXT," +
                "name TEXT, dateCreated TEXT, boxType BOOLEAN, privateLink TEXT, download BOOLEAN, favourite BOOLEAN, author TEXT)"

        val sectionQuery = "CREATE TABLE sections(Id TEXT," +
                "name TEXT, dateCreated TEXT, color TEXT, favourite BOOLEAN, box INTEGER, FOREIGN KEY (box) REFERENCES boxes(Id))"

        val itemQuery = "CREATE TABLE items(Id TEXT," +
                "name TEXT, dateCreated TEXT, color TEXT, description TEXT, amount INTEGER, picture TEXT, favourite BOOLEAN," +
                "box INTEGER, section INTEGER, FOREIGN KEY (box) REFERENCES boxes(Id), FOREIGN KEY (section) REFERENCES sections(Id))"

        p0!!.execSQL(boxQuery)
        p0!!.execSQL(sectionQuery)
        p0!!.execSQL(itemQuery)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        val query2 = "drop table boxes"
        p0!!.execSQL(query2)
        onCreate(p0)
    }

    fun addBox(b:BoxData){
        val data = ContentValues()
        data.put("id", b.id)
        data.put("name", b.name)
        data.put("dateCreated", b.dateCreated)
        data.put("boxType", b.boxType)
        data.put("privateLink", b.privateLink)
        data.put("download", b.download)
        data.put("favourite", b.favourite)
        data.put("author", b.author)
        val db = this.writableDatabase.insert("boxes", null, data)
    }

    fun addSection(s:SectionData){
        val data = ContentValues()
        data.put("id", s.id)
        data.put("name", s.name)
        data.put("dateCreated", s.dateCreated)
        data.put("color", s.color)
        data.put("favourite", s.favourite)
        data.put("box", s.boxId)
        val db = this.writableDatabase.insert("sections", null, data)
    }

    fun addItem(i:ItemData){
        val data = ContentValues()
        data.put("id", i.id)
        data.put("name", i.name)
        data.put("dateCreated", i.dateCreated)
        data.put("color", i.color)
        data.put("color", i.description)
        data.put("color", i.amount)
        data.put("color", i.picture)
        data.put("favourite", i.favourite)
        data.put("box", i.boxId)
        data.put("box", i.sectionId)
        val db = this.writableDatabase.insert("items", null, data)
    }

    fun fetchNewsFeedBox(liveData: MutableLiveData<List<BoxData>>) {
        val db = this.readableDatabase
        val query = "SELECT * FROM boxes"
        val result = db.rawQuery(query, null)

        val boxList = mutableListOf<BoxData>()

        if(result.moveToFirst()){
            do {
                boxList.add(
                    BoxData(
                        result.getString(0), result.getString(1), result.getString(2), result.getInt(3) == 1,
                        result.getString(4), result.getInt(5) == 1, result.getInt(6) == 1, result.getString(7))
                )
            } while (result.moveToNext())
        }
        liveData.postValue(boxList)
    }
}