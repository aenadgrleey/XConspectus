package com.example.xconspectus.ui.chapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.xconspectus.databinding.ChapterFragmentBinding
import com.example.xconspectus.ui.text_editor.TextEditor

class ChapterFragment : Fragment() {
    private lateinit var binding: ChapterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ChapterFragmentBinding.inflate(inflater)
        binding.editorTest.setOnClickListener(){
            (it as EditText).addTextChangedListener(TextEditor(it))
            
        }
        return binding.root
    }


}