package com.example.checklistapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.R
import com.example.checklistapp.activity.CheckListActivity
import com.example.checklistapp.model.Item

class CheckListAdapter(val context: Context, val itemList: MutableList<Item>) :
    RecyclerView.Adapter<CheckListAdapter.CheckListViewHolder>() {

    private val list: MutableList<Item> = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckListViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.checklist_item, parent, false)
        return CheckListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListViewHolder, position: Int) {
        holder.ItemName.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class CheckListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ItemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        val ItemCheckBox = itemView.findViewById<CheckBox>(R.id.cb_item)
    }

}
