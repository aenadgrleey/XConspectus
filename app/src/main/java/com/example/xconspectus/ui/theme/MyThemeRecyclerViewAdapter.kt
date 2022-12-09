package com.example.xconspectus.ui.theme

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.xconspectus.data.ChapterDB
import com.example.xconspectus.databinding.ThemeFragmentItemBinding

class MyThemeRecyclerViewAdapter(private val parentFragment: ThemeFragment) :
    RecyclerView.Adapter<MyThemeRecyclerViewAdapter.ViewHolder>() {

    var chapters : List<ChapterDB> = listOf()

    inner class ViewHolder(binding: ThemeFragmentItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val chapterId = binding.itemNumber
        val chapterName = binding.content

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyThemeRecyclerViewAdapter.ViewHolder {
        return ViewHolder(
            ThemeFragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = chapters[position]

        //set up item view
        holder.chapterId.text = item.id.toString()
        holder.chapterName.text = item.name

        //set up navigation to chapter
        holder.itemView.setOnClickListener{
            val action = ThemeFragmentDirections.actionThemeFragmentToChapterFragment(item.id!!)
            parentFragment.requireView().findNavController().navigate(action)
        }
        holder.itemView.setOnLongClickListener{
            parentFragment.changeChapter(item)
            return@setOnLongClickListener true
        }
    }

    override fun getItemCount(): Int = chapters.size

    fun setData(chapters : List<ChapterDB>) {
        this.chapters = chapters
        this.notifyDataSetChanged()
    }

}