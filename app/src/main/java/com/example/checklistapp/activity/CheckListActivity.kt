package com.example.checklistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.checklistapp.adapter.CheckListAdapter
import com.example.checklistapp.api.Api
import com.example.checklistapp.databinding.ActivityChecklistBinding
import com.example.checklistapp.model.Item
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val call = Api.RETROFIT_SERVICE.addItem(item.name,item.checked)
        call.enqueue(object : Callback<Item?> {
            override fun onResponse(call: Call<Item?>, response: Response<Item?>) {
                    Toast.makeText(this@CheckListActivity,"Success",Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Item?>, t: Throwable) {
                    Toast.makeText(this@CheckListActivity,t.message,Toast.LENGTH_LONG).show()
            }
        })
//        itemLists.add(item)
    }


}