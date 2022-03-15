package com.example.checklistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklistapp.R
import com.example.checklistapp.adapter.CheckListAdapter
import com.example.checklistapp.databinding.ActivityChecklistBinding
import com.example.checklistapp.model.Item

class CheckListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChecklistBinding
    private lateinit var itemLists: MutableList<Item>
    private lateinit var adapter: CheckListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        setContentView(binding.root)
        itemLists = mutableListOf()

//        Setting Recycler view
        setRecView()
//        Listeners
        setListeners()


    }

    private fun setListeners() {
        binding.btAddItem.setOnClickListener {
            var itemName = binding.etItem.text.toString()
            if (itemName != "") {
                insertItem(itemName)
                binding.etItem.text.clear()
            }else {
                binding.etItem.setError("Enter Item Name")
            }
            adapter.notifyDataSetChanged()
        }
    }

    private fun setRecView() {
        val checkListRec = binding.rvChecklists
        adapter = CheckListAdapter(context = this, itemLists)
        checkListRec.adapter = adapter
        checkListRec.layoutManager = LinearLayoutManager(this)
    }

    private fun insertItem(itemName: String) {
        val item = Item(itemName)
        itemLists.add(item)
    }


}