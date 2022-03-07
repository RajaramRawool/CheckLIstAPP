package com.example.checklistapp.appdatabase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

val DATABASE_NAME = "CHECKLISTS"
val TABLE_NAME = "CHECKLIST"
val COLUMN_ID = "ID"
val COLUMN_ITEM = "ITEM"

class AppDbHelper(private val context: Context) : SQLiteOpenHelper(context,DATABASE_NAME,null,1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ITEM + " VARCHAR(256))"
        db?.execSQL(createTable)
    }

    fun writeData(item:String) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ITEM,item)
        db.insert(TABLE_NAME,null,cv)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}