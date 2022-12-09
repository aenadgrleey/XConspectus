package com.example.xconspectus.ui.subject

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.xconspectus.data.ThemeDB
import com.example.xconspectus.databinding.SubjectFragmentItemBinding

class MySubjectRecyclerViewAdapter(private val parentFragment: SubjectFragment) :
    RecyclerView.Adapter<MySubjectRecyclerViewAdapter.ViewHolder>() {
    private var themes: List<ThemeDB> = listOf()

    inner class ViewHolder(binding: SubjectFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val themeNumber: TextView = binding.itemNumber
        val themeName: TextView = binding.content
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MySubjectRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            SubjectFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MySubjectRecyclerViewAdapter.ViewHolder, position: Int) {
        val item = themes[position]

        //set up item view
        holder.themeName.text = item.name
        holder.themeNumber.text = item.id.toString()

        //set up navigation to theme
        holder.itemView.setOnClickListener{
            val action = SubjectFragmentDirections.actionSubjectFragmentToThemeFragment(item.id!!, item.subjectId)
            parentFragment.requireView().findNavController().navigate(action)
        }

        //set up theme refactoring
        holder.itemView.setOnLongClickListener {
            parentFragment.changeTheme(item)
            return@setOnLongClickListener true
        }
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