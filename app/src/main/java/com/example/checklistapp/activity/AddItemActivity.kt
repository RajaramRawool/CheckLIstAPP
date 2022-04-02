package com.example.checklistapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.checklistapp.ITEM_NAME
import com.example.checklistapp.R
import com.example.checklistapp.appdatabase.AppDb
import com.example.checklistapp.databinding.ActivityAddItemBinding
import java.lang.System.exit

class AddItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddItemBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        Setting listeners
        setListeners()

    }

    private fun setListeners() {
        binding.btAdd.setOnClickListener {
            val itemName = binding.etItemName.text.toString().trim()
            var isIdentical = false
            when (itemName) {
                "" -> binding.etItemName.setError("Enter Name")
                else -> {
                    val db = AppDb(this)
                    val list = db.getItems()
                    for (i in list) {
                        if (i.name == itemName) {
                            binding.etItemName.setError("Item Already Added")
                            isIdentical = true
                        }
                    }
                    if (!isIdentical) {
                        db.addItem(itemName)
                        startActivity(Intent(this, CheckListActivity::class.java))
                        finish()
                    }
                }

            }
        }
        binding.btCancel.setOnClickListener {
            startActivity(Intent(this, CheckListActivity::class.java))
            finish()
        }
    }
}