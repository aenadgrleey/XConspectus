package com.example.xconspectus.ui.text_editor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.xconspectus.databinding.TextEditorToolbarBinding

class TextEditorToolbar : Fragment() {
    private lateinit var binding: TextEditorToolbarBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = TextEditorToolbarBinding.inflate(inflater)
        return binding.root
    }
}