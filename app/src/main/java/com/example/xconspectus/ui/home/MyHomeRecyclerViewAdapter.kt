package com.example.xconspectus.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.xconspectus.R
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.databinding.HomeFragmentItemBinding

class MyHomeRecyclerViewAdapter(private val fragment: HomeFragment, private val parent: View) :
    RecyclerView.Adapter<MyHomeRecyclerViewAdapter.ViewHolder>() {

    private var subjects: List<SubjectDB> = listOf()

    inner class ViewHolder(binding: HomeFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val subjectName: TextView = binding.content
        val subjectNumber: TextView = binding.itemNumber
        val mainView: View = binding.mainView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            HomeFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = subjects[position]
        holder.subjectNumber.text = "#" + item.id.toString()
        holder.subjectName.text = item.name
        holder.mainView.setOnClickListener() {
            val action = HomeFragmentDirections.actionHomeToSubjectFragment(item.id)
            parent.findNavController().navigate(action)
        }
        holder.mainView.setOnLongClickListener() {
            fragment.changeSubjectRequest(item, position)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    fun setData(subjects_arg: List<SubjectDB>?) {
        if (subjects_arg != null) {
            subjects = subjects_arg
        }
        this.notifyDataSetChanged()
    }
}