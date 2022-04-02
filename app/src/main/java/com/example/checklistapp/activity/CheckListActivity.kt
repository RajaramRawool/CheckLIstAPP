package com.example.checklistapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.R
import com.example.checklistapp.adapter.CheckListAdapter
import com.example.checklistapp.appdatabase.AppDb
import com.example.checklistapp.databinding.ActivityChecklistBinding
import com.example.checklistapp.model.Item
import com.example.checklistapp.util.AppSharedPreference

class CheckListActivity : AppCompatActivity() {
    lateinit var binding: ActivityChecklistBinding
    lateinit var sf: AppSharedPreference
    lateinit var rv:RecyclerView
    lateinit var list : MutableList<Item>
    lateinit var itemAdapter : CheckListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityChecklistBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sf = AppSharedPreference(this)

        rv = binding.rvChecklist
        val db = AppDb(this)
        list = db.getItems()
        itemAdapter = CheckListAdapter(this, list )
        rv.adapter = itemAdapter
        rv.layoutManager = LinearLayoutManager(this)

//      Setting Listeners
        setListeners()

    }

    private fun setListeners() {
        binding.fabAddItem.setOnClickListener {
            startActivity(Intent(this, AddItemActivity::class.java))
            finish()
        }

    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var result = false
        when (item.itemId) {
            R.id.menu_logout -> {
                sf.setUserSession(false)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                result = true
            }
            R.id.menu_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
                result = true
            }
//            R.id.menu_delete_all -> {
//                val db = AppDb(this)
//                if (db.getCounts() != 0 ) {
//
//                    val builder = AlertDialog.Builder(this)
//                    with(builder) {
//                        setTitle("Do you want to delete all items?")
//                        setPositiveButton("Yes") { _, which ->
//                            db.deleteAll()
//                            Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show()
//                            list = db.getItems()
//                            itemAdapter.notifyDataSetChanged()
//                        }
//                        setNegativeButton("No") { _, which ->
//
//                        }
//                        create()
//                        show()
//
//                    }
//                }
//
//            }
            R.id.menu_sync -> {
                // TODO: sync process will start if user is logged in
            }

            else -> result = super.onOptionsItemSelected(item)
        }
        return result

    }


}






