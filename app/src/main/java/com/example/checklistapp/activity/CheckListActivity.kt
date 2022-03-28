package com.example.checklistapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.checklistapp.R
import com.example.checklistapp.databinding.ActivityChecklistBinding
import com.example.checklistapp.util.AppSharedPreference

class CheckListActivity : AppCompatActivity() {
    lateinit var binding: ActivityChecklistBinding
    lateinit var sf : AppSharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sf = AppSharedPreference(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_logout -> { sf.setUserSession(false)
                startActivity(Intent(this,LoginActivity::class.java))
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


}






