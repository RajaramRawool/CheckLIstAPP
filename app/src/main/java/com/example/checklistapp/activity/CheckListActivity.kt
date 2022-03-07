package com.example.checklistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checklistapp.R
import com.example.checklistapp.appdatabase.AppDbHelper

class CheckListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)


        var db = AppDbHelper(this)
        db.writeData("Milk")




    }
}














