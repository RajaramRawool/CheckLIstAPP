package com.example.checklistapp.activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.R
import com.example.checklistapp.model.Item


class CheckListAdapter(private val context: Context, private var itemList: MutableList<Item>) :
    RecyclerView.Adapter<CheckListAdapter.CheckListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckListAdapter.CheckListViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.checklist_item, parent, false)
        return CheckListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CheckListAdapter.CheckListViewHolder, position: Int) {
        val item = itemList[position]
        holder.tvItemName.text = item.name
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class CheckListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvItemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        val cbItem = itemView.findViewById<CheckBox>(R.id.cb_item_checkbox)
    }
}



