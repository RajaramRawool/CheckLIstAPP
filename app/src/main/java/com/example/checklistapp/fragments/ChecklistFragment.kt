package com.example.checklistapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.FontsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.adapter.CheckListAdapter
import com.example.checklistapp.appdatabase.Api
import com.example.checklistapp.appdatabase.AppDb
import com.example.checklistapp.appdatabase.COLUMN_NAME
import com.example.checklistapp.databinding.FragmentChecklistBinding
import com.example.checklistapp.model.Item
import com.example.checklistapp.model.ItemList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChecklistFragment : Fragment() {
    private var _binding: FragmentChecklistBinding? = null
    private val binding get() = _binding!!

    lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChecklistBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    @SuppressLint("Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val rv = binding.rvFragmentChecklist
        val db = AppDb(context)
        val list = db.getItems()
        rv.adapter = CheckListAdapter(list as MutableList<String>)
        rv.layoutManager = LinearLayoutManager(context)

    }
}