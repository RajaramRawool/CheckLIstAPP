package com.example.checklistapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.checklistapp.ID
import com.example.checklistapp.ITEM_NAME
import com.example.checklistapp.R
import com.example.checklistapp.appdatabase.AppDb
import com.example.checklistapp.databinding.ActivityDeleteItemBinding
import java.lang.StringBuilder

class DeleteItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityDeleteItemBinding
    lateinit var itemName: String
    lateinit var id: String
    lateinit var db : AppDb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.etItemNameDelete.setText(intent.getStringExtra(ITEM_NAME).toString())
        itemName = intent.getStringExtra(ITEM_NAME).toString()
        id = intent.getStringExtra(ID).toString()
        db = AppDb(this)
        setListeners()

    }

    private fun setListeners() {
        binding.btUpdate.setOnClickListener {
            db.updateItem(id.toInt(), binding.etItemNameDelete.text.toString(), false)
            finish()
            startActivity(Intent(this, CheckListActivity::class.java))
        }

        binding.btDelete.setOnClickListener {
            db.deleteItem(id.toInt())
            finish()
            startActivity(Intent(this, CheckListActivity::class.java))
        }

    }
}