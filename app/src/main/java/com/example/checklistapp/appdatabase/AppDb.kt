package com.example.checklistapp.appdatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

const val VERSION: Int = 1
const val DATABASE_NAME = "CHECKLIST APP DB"
const val TABLE_NAME = "ITEMS"
const val COLUMN_ID = "Id"
const val COLUMN_NAME = "Name"
const val COLUMN_IS_CHECKED = "isChecked"


class AppDb(val context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null , VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER AUTO_INCREMENT PRIMARY KEY ,$COLUMN_NAME TEXT )")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addItem(name : String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME , name)
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    @SuppressLint("Range")
    fun getItems() : List<String> {
        val db = readableDatabase
        val query = "SELECT * FROM " + TABLE_NAME
        val cursor = db.rawQuery(query,null)
        var list : MutableList<String> = mutableListOf()

        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
            }while (cursor.moveToNext())
        }
        return list
    }
}