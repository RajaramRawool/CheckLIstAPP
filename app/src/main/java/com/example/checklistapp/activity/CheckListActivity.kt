package com.example.checklistapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.checklistapp.databinding.ActivityChecklistBinding

class CheckListActivity : AppCompatActivity() {
    lateinit var binding: ActivityChecklistBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}





