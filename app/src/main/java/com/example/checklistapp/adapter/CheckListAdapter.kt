package com.example.checklistapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.R
import com.example.checklistapp.model.Item

class CheckListAdapter(list:MutableList<Item>) :
    RecyclerView.Adapter<CheckListAdapter.ChecklistViewHolder>() {

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
        if (itemList != null) {
            holder.itemName.text = itemList[position].name
            holder.checkBox.isChecked = itemList[position].isChecked
        }
    }

    override fun getItemCount(): Int {
        return if (itemList == null) {
            0
        } else
            itemList.size
    }


}
