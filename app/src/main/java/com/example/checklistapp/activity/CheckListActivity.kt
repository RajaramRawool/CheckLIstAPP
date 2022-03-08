package com.example.checklistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.appdatabase.AppDbHelper
import com.example.checklistapp.databinding.ActivityChecklistBinding
import com.example.checklistapp.model.Item

class CheckListActivity : AppCompatActivity() {
    lateinit var binding: ActivityChecklistBinding
    var itemList = mutableListOf<Item>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var db = AppDbHelper(this)
        db.writeData("Milk")


//        listeners
        setListeners()
//        Adapter for Recycler view
//




    }


    private fun setListeners() {
//        listener to add items
        binding.btAddItem.setOnClickListener {
            var itemName: String = binding.etItem.text.toString()
            var checklistItem = Item(itemName)
            itemList.add(checklistItem)
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_LONG).show()

            var rvChecklist: RecyclerView = binding.rvChecklists
            rvChecklist.adapter = CheckListAdapter(this, itemList)
            rvChecklist.layoutManager = LinearLayoutManager(this)
        }

    }
}














