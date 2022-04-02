package com.example.checklistapp.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.ID
import com.example.checklistapp.ITEM_NAME
import com.example.checklistapp.R
import com.example.checklistapp.activity.CheckListActivity
import com.example.checklistapp.activity.DeleteItemActivity
import com.example.checklistapp.appdatabase.AppDb
import com.example.checklistapp.model.Item

class CheckListAdapter(val context: Context?, list: MutableList<Item>) :
    RecyclerView.Adapter<CheckListAdapter.ChecklistViewHolder>() {
    private val activity : CheckListActivity = context as CheckListActivity

    var itemList = list


    class ChecklistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        val checkBox = itemView.findViewById<CheckBox>(R.id.cb_item_checkbox)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChecklistViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.checklist_item, parent, false)
        return ChecklistViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChecklistViewHolder, position: Int) {
        holder.itemName.text = itemList[position].name
        holder.checkBox.isChecked = itemList[position].isChecked
        if (holder.checkBox.isChecked) {
            holder.itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        }
        val db = AppDb(context)
        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                holder.itemName.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                holder.itemName.paintFlags = Paint.ANTI_ALIAS_FLAG
                itemList[position].isChecked = holder.checkBox.isChecked
            }
            db.updateItem(itemList[position].id + 1, holder.itemName.text.toString(), holder.checkBox.isChecked)
        }

        holder.itemName.setOnClickListener {
            val intent = Intent(context,DeleteItemActivity::class.java)
            intent.putExtra(ITEM_NAME,holder.itemName.text.toString())
            intent.putExtra(ID,itemList[position].id.toString())
            context?.startActivity(intent)
            activity.finish()
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }


}
