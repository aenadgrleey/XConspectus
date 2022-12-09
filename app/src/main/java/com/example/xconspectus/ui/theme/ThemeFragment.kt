package com.example.xconspectus.ui.theme

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xconspectus.R
import com.example.xconspectus.data.ChapterDB
import com.example.xconspectus.data.repositories.ChaptersRepository
import com.example.xconspectus.databinding.ThemeFragmentBinding
import kotlinx.coroutines.launch

class ThemeFragment : Fragment() {
    private lateinit var binding: ThemeFragmentBinding
    private var adapterToSet = MyThemeRecyclerViewAdapter(this)

    private lateinit var viewModel: ThemeFragmentViewModel
    private val sharedViewModel: ChapterRefactorSharedViewModel by activityViewModels()

    private val args: ThemeFragmentArgs by navArgs()
    private var themeId = 0
    private var subjectId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ThemeFragmentBinding.inflate(inflater)

        themeId = args.themeId
        subjectId = args.subjectId
        val chaptersRepository = ChaptersRepository(requireContext(), themeId)
        val factory = ThemeFragmentViewModel.Factory(chaptersRepository)
        viewModel = ViewModelProvider(this, factory)[ThemeFragmentViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                with(binding.list) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = adapterToSet
                }
            }
        }

        setObservers()
        setListeners()

        return binding.root
    }

    private fun setObservers() {
        //observe themes to detect change in dateset
        viewModel.chapters.observe(viewLifecycleOwner) {
            adapterToSet.setData(it)
        }

        //observe refactoring theme to detect changes
        //maybe should be function
        sharedViewModel.refactored.observe(viewLifecycleOwner) {
            if (it) {
                viewModel.addChapter(sharedViewModel.chapterDB!!)
                sharedViewModel.onAdd()
            }
        }
    }

    private fun setListeners() {
        //set up "add new" button
        binding.fab.setOnClickListener {
            sharedViewModel.setNewChapter(themeId, subjectId)
            findNavController().navigate(R.id.refactorChapter)
        }
    }

    fun changeChapter(chapterDB: ChapterDB) {
        sharedViewModel.setChapterToRefactor(chapterDB)
        findNavController().navigate(R.id.refactorChapter)
    }


}