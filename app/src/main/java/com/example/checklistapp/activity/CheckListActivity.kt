package com.example.checklistapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.checklistapp.R
import com.example.checklistapp.databinding.ActivityChecklistBinding
import com.example.checklistapp.fragments.ChecklistFragment
import com.example.checklistapp.util.AppSharedPreference
import java.lang.System.exit

class CheckListActivity : AppCompatActivity() {
    lateinit var binding: ActivityChecklistBinding
    lateinit var sf : AppSharedPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        sf = AppSharedPreference(this)
//      inflating Fragment to activity
        inflateFragment()
    }

    private fun inflateFragment() {
        val f = ChecklistFragment()
        supportFragmentManager.beginTransaction().replace(R.id.frame,f).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var result = false
        when(item.itemId) {
            R.id.menu_logout -> { sf.setUserSession(false)
                startActivity(Intent(this,LoginActivity::class.java))
                finish()
                result = true
            }
            R.id.menu_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                result = true
            }
            R.id.menu_sync -> {
                // TODO: sync process will start if user is logged in
            }

            else -> result = super.onOptionsItemSelected(item)
        }
        return result

    }


}






