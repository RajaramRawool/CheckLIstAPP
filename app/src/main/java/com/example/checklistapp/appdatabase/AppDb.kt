package com.example.checklistapp.appdatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.checklistapp.model.Item

const val VERSION: Int = 1
const val DATABASE_NAME = "CHECKLIST APP DB"
const val TABLE_NAME = "ITEMS"
const val COLUMN_ID = "id"
const val COLUMN_NAME = "name"
const val COLUMN_IS_CHECKED = "isChecked"


class AppDb(val context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null , VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = ("CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT ,$COLUMN_NAME TEXT , $COLUMN_IS_CHECKED INTEGER DEFAULT 0 )")
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

    fun addItem(name : String, isChecked: Boolean = false) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME , name)
        values.put(COLUMN_IS_CHECKED, isChecked)
        db.insert(TABLE_NAME,null,values)
        db.close()
    }
    @SuppressLint("Range")
    fun getItems() : MutableList<Item> {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)
        val list : MutableList<Item> = mutableListOf()

        if (cursor.moveToFirst()) {
            do {

                val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                val name: String = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                val isChecked: Boolean = cursor.getInt(cursor.getColumnIndex(COLUMN_IS_CHECKED)) == 1

                val item = Item(id, name, isChecked)

                list.add( item)
            }while (cursor.moveToNext())
        }
        return list
    }
    fun getCounts(): Int {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query,null)

        return cursor.count
    }

    fun updateItem(id:Int, name:String, isChecked:Boolean) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_ID,id)
        cv.put(COLUMN_NAME,name)
        cv.put(COLUMN_IS_CHECKED,isChecked)
        db.update(TABLE_NAME,cv,"id = $id",null)
    }

    fun deleteItem(id:Int) {
        val db = writableDatabase
        val result = db.delete(TABLE_NAME,"id = $id",null)

    }

    fun deleteAll(){
        val db = writableDatabase
        val deleteQuery = "DELETE FROM $TABLE_NAME"
        db.execSQL(deleteQuery)

    }
}