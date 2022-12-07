package com.example.xconspectus.ui.subject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.xconspectus.data.ThemeDB
import com.example.xconspectus.databinding.SubjectFragmentItemBinding

class MySubjectRecyclerViewAdapter : RecyclerView.Adapter<MySubjectRecyclerViewAdapter.ViewHolder>() {
    private var themes : List<ThemeDB> = listOf()
    inner class ViewHolder(binding: SubjectFragmentItemBinding) : RecyclerView.ViewHolder(binding.root){
        val themeNumber : TextView = binding.itemNumber
        val themeName : TextView = binding.content
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MySubjectRecyclerViewAdapter.ViewHolder {
        return ViewHolder(SubjectFragmentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MySubjectRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = themes[position]
        holder.themeName.text = item.name
        holder.themeNumber.text = item.id.toString()
    }

    override fun getItemCount(): Int {
        return themes.size
    }
    fun setData(themes_arg: List<ThemeDB>?) {
        if (themes_arg != null) {
            themes = themes_arg
        }
        this.notifyDataSetChanged()
    }
}