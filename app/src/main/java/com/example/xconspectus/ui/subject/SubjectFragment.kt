package com.example.xconspectus.ui.subject

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xconspectus.data.repositories.ThemesRepository
import com.example.xconspectus.databinding.SubjectFragmentBinding
import kotlinx.coroutines.launch


class SubjectFragment : Fragment() {
    private lateinit var binding: SubjectFragmentBinding
    private val adapterToSet = MySubjectRecyclerViewAdapter()
    private lateinit var viewModel: SubjectFragmentViewModel
    private val args: SubjectFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SubjectFragmentBinding.inflate(inflater)

        val subjectId = args.subjectId
        val themesRepository = ThemesRepository(requireContext().applicationContext, subjectId)
        val factory = SubjectFragmentViewModel.Factory(themesRepository)
        viewModel = ViewModelProvider(this, factory)[SubjectFragmentViewModel::class.java]
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                with(binding.list) {
                    layoutManager = LinearLayoutManager(context)
                    adapter = adapterToSet
                }
            }
        }

        setObservers()

        return binding.root
    }

    private fun setObservers() {
        viewModel.themes.observe(viewLifecycleOwner) {
            adapterToSet.setData(it)
        }

    }


}