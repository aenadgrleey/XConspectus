package com.example.xconspectus.ui.text_editor

import alirezat775.lib.carouselview.CarouselAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.xconspectus.databinding.TextEditorSizeSelectItemBinding

class SizeSelectAdapter : CarouselAdapter() {
    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        (holder as MyCarouselHolder).content.text = (getItems()[position] as SizeSelect.MultiplierModel).multiplier.toString()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TextEditorSizeSelectItemBinding.inflate(inflater)
        return MyCarouselHolder(binding)
    }

    inner class MyCarouselHolder(itemBinding: TextEditorSizeSelectItemBinding): CarouselViewHolder(itemBinding.root){
        val content:TextView = itemBinding.content
    }
}