package com.example.xconspectus.ui.chapter

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.xconspectus.R

class ChapterFragment : Fragment() {

    companion object {
        fun newInstance() = ChapterFragment()
    }

    private lateinit var viewModel: ChapterFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chapter_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChapterFragmentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}