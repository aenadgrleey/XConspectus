package com.example.xconspectus.ui.navdrawer

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xconspectus.data.SubjectDB
import com.example.xconspectus.databinding.NavigationDrawerItemBinding

class MyNavigationDrawerSubjectRecyclerViewAdapter :
    RecyclerView.Adapter<MyNavigationDrawerSubjectRecyclerViewAdapter.ViewHolder>() {

    private var subjects: List<SubjectDB> = listOf()

    inner class ViewHolder(binding: NavigationDrawerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var subjectName: TextView = binding.subjectName
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = subjects.get(position)
        holder.subjectName.text = item.name ?: "0"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NavigationDrawerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun onItemLoad(new_subjects: List<SubjectDB>?) {
        if (new_subjects != null) {
            subjects = new_subjects
        }
        notifyDataSetChanged()
    }


}