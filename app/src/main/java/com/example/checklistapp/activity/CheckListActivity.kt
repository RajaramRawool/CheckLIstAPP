package com.example.checklistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.appdatabase.AppDbHelper
import com.example.checklistapp.databinding.ActivityChecklistBinding

class CheckListActivity : AppCompatActivity() {
    lateinit var binding: ActivityChecklistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




    }
}





