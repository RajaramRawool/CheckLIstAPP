package com.example.checklistapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.checklistapp.adapter.CheckListAdapter
import com.example.checklistapp.appdatabase.Api
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        rv = binding.rvFragmentChecklist
        val call = Api.retrofitService.getItems()
        call.enqueue(object : Callback<MutableList<Item>?> {
            override fun onResponse(
                call: Call<MutableList<Item>?>,
                response: Response<MutableList<Item>?>
            ) {
                var list = response.body()
                if (list != null) {
                    val adapter = CheckListAdapter(list)
                    rv.adapter = adapter
                    rv.layoutManager = LinearLayoutManager(context)
                }
            }

            override fun onFailure(call: Call<MutableList<Item>?>, t: Throwable) {

            }
        })

    }
}